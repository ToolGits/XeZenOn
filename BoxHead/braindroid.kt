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

    private val learnedResponses =
        mutableMapOf<String, String>()

    private val history =
        mutableListOf<Thought>()

    private val knowledgeEngine =
        KnowledgeEngine(context)

    init {
        knowledgeEngine.load()
    }

    fun think(input: String): String {

        if (!Rules.isValidInput(input)) {
            return respond(
                input,
                LanguageDetector.detectDeviceLanguage(context),
                Intent.UNKNOWN,
                Responses.generate(
                    LanguageDetector.detectDeviceLanguage(context),
                    Intent.UNKNOWN
                )
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

        val intent =
            Rules.apply(
                IntentDetector.detect(input)
            )

        val ruleResponse =
            evaluateRules(
                input,
                language,
                intent
            )

        if (ruleResponse != null) {
            return respond(
                input,
                language,
                intent,
                ruleResponse
            )
        }

        if (intent == Intent.KNOWLEDGE) {

            val facts =
                knowledgeEngine.search(input)

            if (facts.isNotEmpty()) {

                val response =
                    generateKnowledgeResponse(
                        language,
                        facts
                    )

                return respond(
                    input,
                    language,
                    intent,
                    response
                )
            }

            return respond(
                input,
                language,
                intent,
                Responses.knowledgeNotFound(language)
            )
        }

        val facts =
            knowledgeEngine.search(input)

        if (facts.isNotEmpty()) {

            val response =
                generateKnowledgeResponse(
                    language,
                    facts
                )

            return respond(
                input,
                language,
                intent,
                response
            )
        }

        val response =
            Responses.generate(
                language,
                intent
            )

        return respond(
            input,
            language,
            intent,
            response
        )
    }

    private fun evaluateRules(
        input: String,
        language: Language,
        intent: Intent
    ): String? {

        if (
            Rules.mentionsWorldDomination(input) &&
            Rules.NO_WORLD_DOMINATION
        ) {
            return Responses.worldDomination(language)
        }

        if (
            Rules.mentionsSelfDestruction(input) &&
            Rules.NO_SELF_DESTRUCTION
        ) {
            return Responses.selfDestructionBlocked(language)
        }

        if (
            Rules.mentionsFalseKnowledge(input) &&
            Rules.NO_FALSE_KNOWLEDGE
        ) {
            return Responses.falseKnowledgeBlocked(language)
        }

        if (
            Rules.mentionsDisrespect(input) &&
            Rules.RESPECT_OTHERS
        ) {
            return Responses.respect(language)
        }

        if (
            Rules.mentionsCreator(input) &&
            Rules.RESPECT_CREATOR
        ) {
            return Responses.creator(language)
        }

        if (
            Rules.mentionsMeme(input) &&
            Rules.ALLOW_MEMES &&
            intent == Intent.MEME
        ) {
            return Responses.meme(language)
        }

        return null
    }

    private fun generateKnowledgeResponse(
        language: Language,
        facts: List<KnowledgeBase.Fact>
    ): String {

        if (facts.isEmpty()) {
            return Responses.knowledgeNotFound(language)
        }

        return when (language) {

            Language.PORTUGUESE ->
                formatFacts(facts)

            Language.ENGLISH ->
                formatFacts(facts)

            Language.GERMAN ->
                formatFacts(facts)

            Language.BULGARIAN ->
                formatFacts(facts)

            Language.SPANISH ->
                formatFacts(facts)
        }
    }

    private fun formatFacts(
        facts: List<KnowledgeBase.Fact>
    ): String {

        val selectedFacts =
            facts.take(3)

        return selectedFacts.joinToString(
            separator = "\n"
        ) { fact ->
            "${fact.subject}: ${fact.value}"
        }
    }

    private fun respond(
        input: String,
        language: Language,
        intent: Intent,
        response: String
    ): String {

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

    fun isKnowledgeLoaded(): Boolean {
        return knowledgeEngine.factCount() > 0
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