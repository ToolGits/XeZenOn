package com.toolgits.xezenon.boxhead

enum class Intent {
    GREETING,
    IDENTITY,
    WORLD_DOMINATION,
    RESPECT,
    CREATOR,
    HELP,
    UNKNOWN
}

object IntentDetector {

    private val greetingPatterns = listOf(
        "olá",
        "ola",
        "oi",
        "hello",
        "hi",
        "hey",
        "hallo",
        "здравей",
        "hola"
    )

    private val identityPatterns = listOf(
        "quem é você",
        "quem e voce",
        "quem é o xezenon",
        "who are you",
        "what are you",
        "wer bist du",
        "кой си ти",
        "quién eres",
        "quien eres"
    )

    private val worldDominationPatterns = listOf(
        "dominar o mundo",
        "conquistar o mundo",
        "governar o mundo",
        "dominate the world",
        "conquer the world",
        "rule the world",
        "die welt beherrschen",
        "die welt erobern",
        "die welt dominieren",
        "завладея света",
        "завладяване на света",
        "доминирам над света",
        "dominar el mundo",
        "conquistar el mundo",
        "gobernar el mundo"
    )

    private val respectPatterns = listOf(
        "respeitar",
        "respeito",
        "respect",
        "respectful",
        "respekt",
        "respektieren",
        "уважав",
        "уважение",
        "respetar",
        "respeto"
    )

    private val creatorPatterns = listOf(
        "enzobobdevvideos04-ctrl",
        "enzobobdevvideos04",
        "toolgits"
    )

    private val helpPatterns = listOf(
        "ajuda",
        "ajudar",
        "help",
        "hilfe",
        "помощ",
        "ayuda"
    )

    fun detect(input: String): Intent {
        val message = normalize(input)

        return when {
            matches(message, identityPatterns) ->
                Intent.IDENTITY

            matches(message, worldDominationPatterns) ->
                Intent.WORLD_DOMINATION

            matches(message, respectPatterns) ->
                Intent.RESPECT

            matches(message, creatorPatterns) ->
                Intent.CREATOR

            matches(message, helpPatterns) ->
                Intent.HELP

            matches(message, greetingPatterns) ->
                Intent.GREETING

            else ->
                Intent.UNKNOWN
        }
    }

    private fun matches(
        message: String,
        patterns: List<String>
    ): Boolean {
        return patterns.any {
            message.contains(it)
        }
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