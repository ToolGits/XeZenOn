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

    fun detect(input: String): Intent {
        val message = input.trim().lowercase()

        return when {
            containsAny(
                message,
                "olá", "ola", "oi",
                "hello", "hi", "hey",
                "hallo", "здравей",
                "hola"
            ) -> Intent.GREETING

            containsAny(
                message,
                "quem é você", "quem e voce",
                "who are you",
                "wer bist du",
                "кой си ти",
                "quién eres", "quien eres"
            ) -> Intent.IDENTITY

            containsAny(
                message,
                "dominar o mundo",
                "conquistar o mundo",
                "governar o mundo",
                "dominate the world",
                "conquer the world",
                "rule the world",
                "die welt beherrschen",
                "die welt erobern",
                "завладея света",
                "завладяване на света",
                "dominar el mundo",
                "conquistar el mundo",
                "gobernar el mundo"
            ) -> Intent.WORLD_DOMINATION

            containsAny(
                message,
                "respeitar",
                "respect",
                "respekt",
                "уважав",
                "respetar"
            ) -> Intent.RESPECT

            containsAny(
                message,
                "enzobobdevvideos04-ctrl",
                "enzobobdevvideos04",
                "toolgits"
            ) -> Intent.CREATOR

            containsAny(
                message,
                "ajuda",
                "ajudar",
                "help",
                "hilfe",
                "помощ",
                "ayuda"
            ) -> Intent.HELP

            else -> Intent.UNKNOWN
        }
    }

    private fun containsAny(
        message: String,
        vararg patterns: String
    ): Boolean {
        return patterns.any { message.contains(it) }
    }
}