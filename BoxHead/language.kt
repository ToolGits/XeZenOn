package com.toolgits.xezenon.boxhead

enum class Language {
    PORTUGUESE,
    ENGLISH,
    GERMAN,
    BULGARIAN,
    SPANISH
}

object LanguageDetector {

    fun detect(input: String): Language {
        val message = input.trim().lowercase()

        return when {
            containsAny(
                message,
                "olá", "ola", "oi", "você", "voce",
                "não", "nao", "mundo", "quem"
            ) -> Language.PORTUGUESE

            containsAny(
                message,
                "hello", "hi", "hey", "you",
                "world", "what", "who", "how"
            ) -> Language.ENGLISH

            containsAny(
                message,
                "hallo", "ich", "du", "welt",
                "wer", "was", "wie"
            ) -> Language.GERMAN

            containsAny(
                message,
                "здравей", "свят", "кой", "как",
                "аз", "ти"
            ) -> Language.BULGARIAN

            containsAny(
                message,
                "hola", "mundo", "quién", "quien",
                "qué", "que", "cómo", "como"
            ) -> Language.SPANISH

            else -> Language.PORTUGUESE
        }
    }

    private fun containsAny(
        message: String,
        vararg patterns: String
    ): Boolean {
        return patterns.any { message.contains(it) }
    }
}