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
        "olá" to 6,
        "ola" to 6,
        "oi" to 6,
        "bom dia" to 6,
        "boa tarde" to 6,
        "boa noite" to 6,
        "você" to 4,
        "voce" to 4,
        "não" to 4,
        "nao" to 4,
        "quem é" to 6,
        "quem e" to 6,
        "como está" to 6,
        "como esta" to 6,
        "eu sou" to 5,
        "eu quero" to 5,
        "preciso de" to 5,
        "pode me" to 5,
        "o que é" to 6,
        "o que e" to 6,
        "o que são" to 6,
        "o que sao" to 6,
        "por que" to 5,
        "porque" to 4,
        "quando" to 4,
        "onde" to 4,
        "qual" to 4,
        "quem criou" to 6,
        "me fale sobre" to 6,
        "me diga sobre" to 6,
        "explique" to 5
    )

    private val englishPatterns = mapOf(
        "hello" to 6,
        "hi" to 6,
        "hey" to 6,
        "good morning" to 6,
        "good afternoon" to 6,
        "good evening" to 6,
        "who are you" to 6,
        "how are you" to 6,
        "i am" to 5,
        "i want" to 5,
        "i need" to 5,
        "can you" to 5,
        "what is" to 6,
        "what are" to 6,
        "why" to 4,
        "when" to 4,
        "where" to 4,
        "which" to 4,
        "who created" to 6,
        "tell me about" to 6,
        "what do you know about" to 6,
        "explain" to 5
    )

    private val germanPatterns = mapOf(
        "hallo" to 6,
        "guten morgen" to 6,
        "guten tag" to 6,
        "guten abend" to 6,
        "wer bist du" to 6,
        "wie geht es dir" to 6,
        "wie geht es" to 6,
        "ich bin" to 5,
        "ich möchte" to 5,
        "ich mochte" to 5,
        "ich brauche" to 5,
        "kannst du" to 5,
        "was ist" to 6,
        "was sind" to 6,
        "warum" to 4,
        "wann" to 4,
        "wo" to 4,
        "welche" to 4,
        "wer hat" to 6,
        "wer erstellt" to 6,
        "erzähl mir über" to 6,
        "erzahl mir uber" to 6,
        "was weißt du über" to 6,
        "was weisst du uber" to 6,
        "erkläre" to 5,
        "erklaere" to 5
    )

    private val bulgarianPatterns = mapOf(
        "здравей" to 6,
        "добро утро" to 6,
        "добър ден" to 6,
        "добър вечер" to 6,
        "кой си ти" to 6,
        "как си" to 6,
        "аз съм" to 5,
        "искам" to 5,
        "имам нужда" to 5,
        "можеш ли" to 5,
        "какво е" to 6,
        "какво са" to 6,
        "защо" to 4,
        "кога" to 4,
        "къде" to 4,
        "кой" to 4,
        "кой създаде" to 6,
        "разкажи ми за" to 6,
        "какво знаеш за" to 6,
        "обясни" to 5
    )

    private val spanishPatterns = mapOf(
        "hola" to 6,
        "buenos días" to 6,
        "buenos dias" to 6,
        "buenas tardes" to 6,
        "buenas noches" to 6,
        "quién eres" to 6,
        "quien eres" to 6,
        "cómo estás" to 6,
        "como estas" to 6,
        "yo soy" to 5,
        "quiero" to 5,
        "necesito" to 5,
        "puedes" to 5,
        "qué es" to 6,
        "que es" to 6,
        "qué son" to 6,
        "que son" to 6,
        "por qué" to 5,
        "por que" to 5,
        "cuándo" to 4,
        "cuando" to 4,
        "dónde" to 4,
        "donde" to 4,
        "quién" to 4,
        "quien" to 4,
        "quién creó" to 6,
        "quien creo" to 6,
        "háblame de" to 6,
        "hablame de" to 6,
        "qué sabes sobre" to 6,
        "que sabes sobre" to 6,
        "explica" to 5
    )

    fun detect(
        input: String
    ): Language? {
        return detectDetailed(input)?.language
    }

    fun detectDetailed(
        input: String
    ): LanguageResult? {

        val message =
            normalize(input)

        if (message.isEmpty()) {
            return null
        }

        val scores =
            mapOf(
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

        val ordered =
            scores.entries
                .sortedByDescending {
                    it.value
                }

        val best =
            ordered.firstOrNull()
                ?: return null

        if (best.value <= 0) {
            return null
        }

        val second =
            ordered.getOrNull(1)?.value ?: 0

        val confidence =
            if (best.value == second) {
                best.value
            } else {
                best.value + (best.value - second)
            }

        return LanguageResult(
            language = best.key,
            confidence = confidence
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

            val phrase =
                normalize(entry.key)

            if (
                containsPhrase(
                    message,
                    phrase
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