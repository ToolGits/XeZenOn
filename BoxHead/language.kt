package com.toolgits.xezenon.boxhead

import android.content.Context
import java.text.Normalizer
import java.util.Locale

enum class Language {
    PORTUGUESE,
    ENGLISH,
    GERMAN,
    BULGARIAN,
    SPANISH
}

data class LanguageResult(
    val language: Language,
    val confidence: Int
)

object LanguageDetector {

    private val portuguesePatterns = mapOf(
        "olá" to 5,
        "ola" to 5,
        "oi" to 5,
        "você" to 3,
        "voce" to 3,
        "não" to 3,
        "nao" to 3,
        "quem é" to 5,
        "quem e" to 5,
        "como está" to 5,
        "como esta" to 5,
        "eu sou" to 4,
        "eu quero" to 4,
        "preciso de" to 4,
        "pode me" to 4,
        "o que" to 4,
        "por que" to 4,
        "porque" to 3,
        "quando" to 3,
        "onde" to 3,
        "qual" to 3
    )

    private val englishPatterns = mapOf(
        "hello" to 5,
        "hi" to 5,
        "hey" to 5,
        "who are you" to 5,
        "how are you" to 5,
        "i am" to 4,
        "i want" to 4,
        "i need" to 4,
        "can you" to 4,
        "what is" to 5,
        "what are" to 5,
        "why" to 3,
        "when" to 3,
        "where" to 3,
        "which" to 3
    )

    private val germanPatterns = mapOf(
        "hallo" to 5,
        "guten morgen" to 5,
        "guten tag" to 5,
        "wer bist du" to 5,
        "wie geht es" to 5,
        "ich bin" to 4,
        "ich möchte" to 4,
        "ich mochte" to 4,
        "ich brauche" to 4,
        "kannst du" to 4,
        "was ist" to 5,
        "warum" to 3,
        "wann" to 3,
        "wo" to 3,
        "welche" to 3
    )

    private val bulgarianPatterns = mapOf(
        "здравей" to 5,
        "добро утро" to 5,
        "добър ден" to 5,
        "кой си ти" to 5,
        "как си" to 5,
        "аз съм" to 4,
        "искам" to 4,
        "имам нужда" to 4,
        "можеш ли" to 4,
        "какво е" to 5,
        "защо" to 3,
        "кога" to 3,
        "къде" to 3,
        "кой" to 3
    )

    private val spanishPatterns = mapOf(
        "hola" to 5,
        "buenos días" to 5,
        "buenos dias" to 5,
        "buenas tardes" to 5,
        "quién eres" to 5,
        "quien eres" to 5,
        "cómo estás" to 5,
        "como estas" to 5,
        "yo soy" to 4,
        "quiero" to 4,
        "necesito" to 4,
        "puedes" to 4,
        "qué es" to 5,
        "que es" to 5,
        "por qué" to 4,
        "por que" to 4,
        "cuándo" to 3,
        "cuando" to 3,
        "dónde" to 3,
        "donde" to 3,
        "quién" to 3,
        "quien" to 3
    )

    fun detect(
        input: String
    ): Language? {
        return detectDetailed(input)?.language
    }

    fun detectDetailed(
        input: String
    ): LanguageResult? {

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

        val best = scores.maxByOrNull {
            it.value
        }

        if (best == null || best.value <= 0) {
            return null
        }

        return LanguageResult(
            language = best.key,
            confidence = best.value
        )
    }

    fun detectDeviceLanguage(
        context: Context
    ): Language {

        val locales =
            context.resources.configuration.locales

        for (index in 0 until locales.size()) {

            val locale =
                locales[index]

            val language =
                fromLocale(locale)

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

        return when (
            locale.language.lowercase()
        ) {
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
        patterns: Map<String, Int>
    ): Int {

        return patterns.entries.sumOf { entry ->

            if (
                containsPhrase(
                    message,
                    normalize(entry.key)
                )
            ) {
                entry.value
            } else {
                0
            }
        }
    }

    private fun containsPhrase(
        message: String,
        phrase: String
    ): Boolean {

        return message == phrase ||
            message.startsWith("$phrase ") ||
            message.endsWith(" $phrase") ||
            message.contains(" $phrase ")
    }

    private fun normalize(
        input: String
    ): String {

        return Normalizer.normalize(
            input.trim().lowercase(),
            Normalizer.Form.NFD
        )
            .replace(
                Regex("\\p{InCombiningDiacriticalMarks}+"),
                ""
            )
            .replace(
                Regex("[^\\p{L}\\p{N}\\s]"),
                " "
            )
            .replace(
                Regex("\\s+"),
                " "
            )
            .trim()
    }
}