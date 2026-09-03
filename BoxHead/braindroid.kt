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

    private val knowledgeEngine =
        KnowledgeEngine(context)

    init {
        knowledgeEngine.load()
    }

    fun think(input: String): String {

        if (!Rules.isValidInput(input)) {
            return Responses.generate(
                LanguageDetector.detectDeviceLanguage(context),
                Intent.UNKNOWN
            )
        }

        val normalizedInput =
            normalize(input)

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

        val facts =
            knowledgeEngine.search(input)

        if (facts.isNotEmpty()) {

            val response =
                generateKnowledgeResponse(
                    language,
                    facts
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

    private fun generateKnowledgeResponse(
        language: Language,
        facts: List<KnowledgeBase.Fact>
    ): String {

        val fact =
            facts.first()

        return when (language) {

            Language.PORTUGUESE ->
                "${fact.subject}: ${fact.value}"

            Language.ENGLISH ->
                "${fact.subject}: ${fact.value}"

            Language.GERMAN ->
                "${fact.subject}: ${fact.value}"

            Language.BULGARIAN ->
                "${fact.subject}: ${fact.value}"

            Language.SPANISH ->
                "${fact.subject}: ${fact.value}"
        }
    }

    fun learn(
        input: String,
        response: String
    ) {
        val normalizedInput =
            normalize(input)

        val normalizedResponse =
            response.trim()

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

    fun knowledgeCount(): Int {
        return knowledgeEngine.factCount()
    }

    fun reloadKnowledge() {
        knowledgeEngine.reload()
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