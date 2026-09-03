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

            val language =
                LanguageDetector.detectDeviceLanguage(context)

            return respond(
                input,
                language,
                Intent.UNKNOWN,
                Responses.generate(
                    language,
                    Intent.UNKNOWN
                )
            )
        }

        val normalizedInput =
            normalize(input)

        val languageResult =
            LanguageDetector.detectDetailed(input)

        val language =
            languageResult?.language
                ?: LanguageDetector.detectDeviceLanguage(context)

        learnedResponses[normalizedInput]?.let {
            return respond(
                input,
                language,
                Intent.UNKNOWN,
                it
            )
        }

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

        if (
            shouldUseKnowledge(
                input,
                intent,
                language
            )
        ) {

            val facts =
                knowledgeEngine.search(input)

            if (facts.isNotEmpty()) {

                val relevantFacts =
                    selectRelevantFacts(
                        input,
                        facts
                    )

                if (relevantFacts.isNotEmpty()) {

                    val response =
                        generateKnowledgeResponse(
                            language,
                            relevantFacts
                        )

                    return respond(
                        input,
                        language,
                        intent,
                        response
                    )
                }
            }

            if (intent == Intent.KNOWLEDGE) {

                return respond(
                    input,
                    language,
                    intent,
                    Responses.knowledgeNotFound(
                        language
                    )
                )
            }
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

    private fun shouldUseKnowledge(
        input: String,
        intent: Intent,
        language: Language
    ): Boolean {

        if (intent == Intent.KNOWLEDGE) {
            return true
        }

        val message =
            normalize(input)

        val patterns =
            when (language) {

                Language.PORTUGUESE ->
                    portugueseKnowledgePatterns

                Language.ENGLISH ->
                    englishKnowledgePatterns

                Language.GERMAN ->
                    germanKnowledgePatterns

                Language.BULGARIAN ->
                    bulgarianKnowledgePatterns

                Language.SPANISH ->
                    spanishKnowledgePatterns
            }

        return patterns.any {
            containsPhrase(
                message,
                normalize(it)
            )
        }
    }

    private val portugueseKnowledgePatterns =
        listOf(
            "o que é",
            "o que são",
            "quem é",
            "quem criou",
            "quem fez",
            "quando começou",
            "quando foi criado",
            "onde fica",
            "me fale sobre",
            "me diga sobre",
            "o que você sabe sobre",
            "explique",
            "explique sobre"
        )

    private val englishKnowledgePatterns =
        listOf(
            "what is",
            "what are",
            "who is",
            "who created",
            "who made",
            "when did",
            "when was",
            "where is",
            "tell me about",
            "what do you know about",
            "explain",
            "explain about"
        )

    private val germanKnowledgePatterns =
        listOf(
            "was ist",
            "was sind",
            "wer ist",
            "wer hat",
            "wer erstellt",
            "wann begann",
            "wann wurde",
            "wo ist",
            "erzähl mir über",
            "erzahl mir uber",
            "was weißt du über",
            "was weisst du uber",
            "erkläre",
            "erklaere"
        )

    private val bulgarianKnowledgePatterns =
        listOf(
            "какво е",
            "какво са",
            "кой е",
            "кой създаде",
            "кой направи",
            "кога започна",
            "кога е създаден",
            "къде е",
            "разкажи ми за",
            "какво знаеш за",
            "обясни"
        )

    private val spanishKnowledgePatterns =
        listOf(
            "qué es",
            "que es",
            "qué son",
            "que son",
            "quién es",
            "quien es",
            "quién creó",
            "quien creo",
            "quién hizo",
            "quien hizo",
            "cuándo comenzó",
            "cuando comenzo",
            "cuándo fue creado",
            "cuando fue creado",
            "dónde está",
            "donde esta",
            "háblame de",
            "hablame de",
            "qué sabes sobre",
            "que sabes sobre",
            "explica"
        )

    private fun selectRelevantFacts(
        query: String,
        facts: List<KnowledgeBase.Fact>
    ): List<KnowledgeBase.Fact> {

        if (facts.isEmpty()) {
            return emptyList()
        }

        val queryWords =
            tokenize(query)

        return facts
            .map { fact ->

                val subject =
                    normalize(fact.subject)

                val relation =
                    normalize(fact.relation)

                val value =
                    normalize(fact.value)

                val keywords =
                    fact.keywords.map {
                        normalize(it)
                    }

                var score = 0

                if (
                    queryWords.any {
                        subject.contains(it)
                    }
                ) {
                    score += 20
                }

                if (
                    queryWords.any {
                        relation.contains(it)
                    }
                ) {
                    score += 10
                }

                for (word in queryWords) {

                    if (
                        keywords.any {
                            it.contains(word)
                        }
                    ) {
                        score += 5
                    }

                    if (
                        value.contains(word)
                    ) {
                        score += 1
                    }
                }

                fact to score
            }
            .filter {
                it.second > 0
            }
            .sortedByDescending {
                it.second
            }
            .take(3)
            .map {
                it.first
            }
    }

    private fun tokenize(
        input: String
    ): List<String> {

        return normalize(input)
            .replace(
                Regex("[^\\p{L}\\p{N}\\s]"),
                " "
            )
            .split(
                Regex("\\s+")
            )
            .filter {
                it.length >= 2
            }
            .distinct()
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

        return facts
            .take(3)
            .joinToString(
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

    private fun containsPhrase(
        message: String,
        phrase: String
    ): Boolean {

        val paddedMessage =
            " $message "

        val paddedPhrase =
            " $phrase "

        return paddedMessage.contains(
            paddedPhrase
        )
    }

    private fun normalize(
        input: String
    ): String {

        return input
            .trim()
            .lowercase()
            .replace(
                Regex("\\s+"),
                " "
            )
    }
}