package com.toolgits.xezenon.boxhead

import android.content.Context

class Braindroid(
    private val context: Context
) {

    data class Thought(
        val input: String,
        val language: Language,
        val intent: Intent,
        val response: String
    )

    private val learnedResponses = mutableMapOf<String, String>()
    private val history = mutableListOf<Thought>()

    fun think(input: String): String {
        if (!Rules.isValidInput(input)) {
            return Responses.generate(
                LanguageDetector.detectDeviceLanguage(context),
                Intent.UNKNOWN
            )
        }

        val normalizedInput = normalize(input)

        learnedResponses[normalizedInput]?.let {
            return it
        }

        val language =
            LanguageDetector.detect(input)
                ?: LanguageDetector.detectDeviceLanguage(context)

        val detectedIntent =
            IntentDetector.detect(input)

        val intent =
            Rules.apply(detectedIntent)

        val response =
            Responses.generate(
                language,
                intent
            )

        history.add(
            Thought(
                input = input,
                language = language,
                intent = intent,
                response = response
            )
        )

        return response
    }

    fun learn(
        input: String,
        response: String
    ) {
        val normalizedInput = normalize(input)
        val normalizedResponse = response.trim()

        if (
            normalizedInput.isEmpty() ||
            normalizedResponse.isEmpty()
        ) {
            return
        }

        learnedResponses[normalizedInput] =
            normalizedResponse
    }

    fun hasLearned(
        input: String
    ): Boolean {
        return learnedResponses.containsKey(
            normalize(input)
        )
    }

    fun forget(
        input: String
    ) {
        learnedResponses.remove(
            normalize(input)
        )
    }

    fun learnedCount(): Int {
        return learnedResponses.size
    }

    fun history(): List<Thought> {
        return history.toList()
    }

    fun clearHistory() {
        history.clear()
    }

    fun clearLearnedKnowledge() {
        learnedResponses.clear()
    }

    private fun normalize(
        input: String
    ): String {
        return input
            .trim()
            .lowercase()
            .replace(Regex("\\s+"), " ")
    }
}