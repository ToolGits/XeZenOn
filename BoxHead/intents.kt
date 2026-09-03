package com.toolgits.xezenon.boxhead

enum class Intent {
    GREETING,
    IDENTITY,
    WORLD_DOMINATION,
    RESPECT,
    CREATOR,
    HELP,
    THANKS,
    FAREWELL,
    KNOWLEDGE,
    MEME,
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
        "quem e o xezenon",
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
        "respeite",
        "respeitam",
        "respect",
        "respectful",
        "respecting",
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
        "me ajude",
        "pode ajudar",
        "help",
        "help me",
        "can you help",
        "hilfe",
        "hilf mir",
        "помощ",
        "помогни",
        "ayuda",
        "ayúdame",
        "puedes ayudar"
    )

    private val thanksPatterns = listOf(
        "obrigado",
        "obrigada",
        "valeu",
        "muito obrigado",
        "thanks",
        "thank you",
        "thx",
        "danke",
        "dankeschön",
        "благодаря",
        "gracias",
        "muchas gracias"
    )

    private val farewellPatterns = listOf(
        "tchau",
        "até logo",
        "ate logo",
        "até mais",
        "ate mais",
        "adeus",
        "bye",
        "goodbye",
        "see you",
        "tschüss",
        "auf wiedersehen",
        "довиждане",
        "чао",
        "adiós",
        "hasta luego"
    )

    private val knowledgePatterns = listOf(
        "o que é",
        "o que e",
        "o que são",
        "o que sao",
        "como funciona",
        "explique",
        "explica",
        "explique-me",
        "me explique",
        "por que",
        "porque",
        "what is",
        "what are",
        "how does",
        "how do",
        "explain",
        "why",
        "was ist",
        "wie funktioniert",
        "warum",
        "какво е",
        "как работи",
        "защо",
        "qué es",
        "que es",
        "cómo funciona",
        "como funciona",
        "por qué",
        "porque"
    )

    private val memePatterns = listOf(
        "kkkk",
        "kkkkk",
        "kkkkkk",
        "rsrs",
        "rsrsrs",
        "hahaha",
        "haha",
        "lol",
        "lmao",
        "xd",
        "bruh",
        "bro",
        "💀",
        "😂"
    )

    fun detect(input: String): Intent {

        val message = normalize(input)

        if (message.isEmpty()) {
            return Intent.UNKNOWN
        }

        return when {

            matchesPhrase(
                message,
                identityPatterns
            ) ->
                Intent.IDENTITY

            matchesPhrase(
                message,
                worldDominationPatterns
            ) ->
                Intent.WORLD_DOMINATION

            matchesPhrase(
                message,
                creatorPatterns
            ) ->
                Intent.CREATOR

            matchesPhrase(
                message,
                respectPatterns
            ) ->
                Intent.RESPECT

            matchesPhrase(
                message,
                helpPatterns
            ) ->
                Intent.HELP

            matchesPhrase(
                message,
                thanksPatterns
            ) ->
                Intent.THANKS

            matchesPhrase(
                message,
                farewellPatterns
            ) ->
                Intent.FAREWELL

            matchesPhrase(
                message,
                knowledgePatterns
            ) ->
                Intent.KNOWLEDGE

            matchesWord(
                message,
                greetingPatterns
            ) ->
                Intent.GREETING

            matchesWord(
                message,
                memePatterns
            ) ->
                Intent.MEME

            else ->
                Intent.UNKNOWN
        }
    }

    private fun matchesPhrase(
        message: String,
        patterns: List<String>
    ): Boolean {
        return patterns.any {
            containsPhrase(
                message,
                normalize(it)
            )
        }
    }

    private fun matchesWord(
        message: String,
        patterns: List<String>
    ): Boolean {
        val words =
            message.split(" ")

        return patterns.any {
            normalize(it) in words
        }
    }

    private fun containsPhrase(
        message: String,
        phrase: String
    ): Boolean {

        if (phrase.isEmpty()) {
            return false
        }

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
            .replace(Regex("\\s+"), " ")
    }
}