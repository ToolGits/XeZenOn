package com.toolgits.xezenon.boxhead

class Braindroid {

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
                Language.PORTUGUESE,
                Intent.UNKNOWN
            )
        }

        val normalizedInput = input.trim().lowercase()

        learnedResponses[normalizedInput]?.let { learned ->
            return learned
        }

        val language = LanguageDetector.detect(input)
        val detectedIntent = IntentDetector.detect(input)
        val intent = Rules.apply(detectedIntent)

        val response = Responses.generate(
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
        val normalizedInput = input.trim().lowercase()
        val normalizedResponse = response.trim()

        if (
            normalizedInput.isEmpty() ||
            normalizedResponse.isEmpty()
        ) {
            return
        }

        learnedResponses[normalizedInput] = normalizedResponse
    }

    fun hasLearned(input: String): Boolean {
        return learnedResponses.containsKey(
            input.trim().lowercase()
        )
    }

    fun forget(input: String) {
        learnedResponses.remove(
            input.trim().lowercase()
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
}