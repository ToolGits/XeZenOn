package com.toolgits.xezenon.boxhead

import android.content.Context
import java.util.Locale

enum class Language {
    PORTUGUESE,
    ENGLISH,
    GERMAN,
    BULGARIAN,
    SPANISH
}

object LanguageDetector {

    private val portuguesePatterns = listOf(
        "olá",
        "ola",
        "oi",
        "você",
        "voce",
        "não",
        "nao",
        "quem é",
        "quem e",
        "como está",
        "como esta",
        "eu sou",
        "eu quero",
        "preciso de",
        "pode me",
        "o que"
    )

    private val englishPatterns = listOf(
        "hello",
        "hi",
        "hey",
        "who are you",
        "how are you",
        "i am",
        "i want",
        "i need",
        "can you",
        "what is",
        "what are"
    )

    private val germanPatterns = listOf(
        "hallo",
        "guten morgen",
        "guten tag",
        "wer bist du",
        "wie geht es",
        "ich bin",
        "ich möchte",
        "ich mochte",
        "ich brauche",
        "kannst du",
        "was ist"
    )

    private val bulgarianPatterns = listOf(
        "здравей",
        "добро утро",
        "добър ден",
        "кой си ти",
        "как си",
        "аз съм",
        "искам",
        "имам нужда",
        "можеш ли",
        "какво е"
    )

    private val spanishPatterns = listOf(
        "hola",
        "buenos días",
        "buenos dias",
        "buenas tardes",
        "quién eres",
        "quien eres",
        "cómo estás",
        "como estas",
        "yo soy",
        "quiero",
        "necesito",
        "puedes",
        "qué es",
        "que es"
    )

    fun detect(input: String): Language? {
        val message = normalize(input)

        if (message.isEmpty()) {
            return null
        }

        val scores = mapOf(
            Language.PORTUGUESE to score(
                message,
                portuguesePatterns
            ),
            Language.ENGLISH to score(
                message,
                englishPatterns
            ),
            Language.GERMAN to score(
                message,
                germanPatterns
            ),
            Language.BULGARIAN to score(
                message,
                bulgarianPatterns
            ),
            Language.SPANISH to score(
                message,
                spanishPatterns
            )
        )

        val best = scores.maxByOrNull { it.value }

        return if (best != null && best.value > 0) {
            best.key
        } else {
            null
        }
    }

    fun detectDeviceLanguage(
        context: Context
    ): Language {
        val locales = context.resources.configuration.locales

        for (index in 0 until locales.size()) {
            val locale = locales[index]
            val language = fromLocale(locale)

            if (language != null) {
                return language
            }
        }

        return Language.ENGLISH
    }

    fun availableLanguages(): Set<Language> {
        return Language.entries.toSet()
    }

    private fun fromLocale(
        locale: Locale
    ): Language? {
        return when (locale.language.lowercase()) {
            "pt" -> Language.PORTUGUESE
            "en" -> Language.ENGLISH
            "de" -> Language.GERMAN
            "bg" -> Language.BULGARIAN
            "es" -> Language.SPANISH
            else -> null
        }
    }

    private fun score(
        message: String,
        patterns: List<String>
    ): Int {
        return patterns.count {
            containsPhrase(message, it)
        }
    }

    private fun containsPhrase(
        message: String,
        phrase: String
    ): Boolean {
        return message.contains(
            " $phrase "
        ) ||
        message.startsWith("$phrase ") ||
        message.endsWith(" $phrase") ||
        message == phrase
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