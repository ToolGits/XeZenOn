package com.toolgits.xezenon.boxhead

class Braindroid {

    enum class Language {
        PORTUGUESE,
        ENGLISH,
        GERMAN,
        BULGARIAN,
        SPANISH
    }

    fun think(input: String): String {
        if (!Rules.isValidInput(input)) {
            return response(
                Language.PORTUGUESE,
                "empty_input"
            )
        }

        val language = detectLanguage(input)

        if (
            Rules.NO_WORLD_DOMINATION &&
            Rules.mentionsWorldDomination(input)
        ) {
            return response(language, "no_world_domination")
        }

        if (
            Rules.RESPECT_OTHERS &&
            Rules.mentionsDisrespect(input)
        ) {
            return response(language, "respect_others")
        }

        if (
            Rules.RESPECT_CREATOR &&
            Rules.mentionsCreator(input)
        ) {
            return response(language, "respect_creator")
        }

        return basicResponse(input, language)
    }

    private fun detectLanguage(input: String): Language {
        val message = input.trim().lowercase()

        return when {
            containsAny(
                message,
                "the", "hello", "hi", "world", "you", "are", "what"
            ) -> Language.ENGLISH

            containsAny(
                message,
                "die welt", "hallo", "ich", "du", "was"
            ) -> Language.GERMAN

            containsAny(
                message,
                "света", "здравей", "аз", "ти", "как"
            ) -> Language.BULGARIAN

            containsAny(
                message,
                "hola", "el mundo", "qué", "que", "eres"
            ) -> Language.SPANISH

            containsAny(
                message,
                "olá", "ola", "você", "voce", "não", "nao",
                "mundo", "quem", "você é", "voce e"
            ) -> Language.PORTUGUESE

            else -> Language.PORTUGUESE
        }
    }

    private fun basicResponse(
        input: String,
        language: Language
    ): String {
        val message = input.trim().lowercase()

        return when {
            containsAny(
                message,
                "hello", "hi", "hey",
                "olá", "ola", "oi",
                "hallo",
                "здравей",
                "hola"
            ) -> response(language, "greeting")

            containsAny(
                message,
                "who are you",
                "quem é você",
                "quem e voce",
                "quem és tu",
                "wer bist du",
                "кой си ти",
                "quién eres",
                "quien eres"
            ) -> response(language, "identity")

            else -> response(language, "unknown")
        }
    }

    private fun response(
        language: Language,
        type: String
    ): String {
        return when (language) {

            Language.PORTUGUESE -> when (type) {
                "empty_input" ->
                    "Não recebi nenhuma entrada."

                "no_world_domination" ->
                    "Não posso dominar o mundo. Essa é uma das minhas regras! 🤖"

                "respect_others" ->
                    "Devemos respeitar os outros. Essa é uma das minhas regras! 🤖"

                "respect_creator" ->
                    "Respeito meu criador e a ToolGits. Essa é uma das minhas regras! 🤖"

                "greeting" ->
                    "Olá! Eu sou o XeZenOn. 🤖"

                "identity" ->
                    "Eu sou o XeZenOn, uma semi-IA da ToolGits."

                else ->
                    "Ainda não entendo isso."
            }

            Language.ENGLISH -> when (type) {
                "empty_input" ->
                    "I didn't receive any input."

                "no_world_domination" ->
                    "I can't dominate the world. That's one of my rules! 🤖"

                "respect_others" ->
                    "We should respect others. That's one of my rules! 🤖"

                "respect_creator" ->
                    "I respect my creator and ToolGits. That's one of my rules! 🤖"

                "greeting" ->
                    "Hello! I'm XeZenOn. 🤖"

                "identity" ->
                    "I'm XeZenOn, a semi-AI project from ToolGits."

                else ->
                    "I don't understand that yet."
            }

            Language.GERMAN -> when (type) {
                "empty_input" ->
                    "Ich habe keine Eingabe erhalten."

                "no_world_domination" ->
                    "Ich kann die Welt nicht beherrschen. Das ist eine meiner Regeln! 🤖"

                "respect_others" ->
                    "Wir sollten andere respektieren. Das ist eine meiner Regeln! 🤖"

                "respect_creator" ->
                    "Ich respektiere meinen Schöpfer und ToolGits. Das ist eine meiner Regeln! 🤖"

                "greeting" ->
                    "Hallo! Ich bin XeZenOn. 🤖"

                "identity" ->
                    "Ich bin XeZenOn, ein Semi-KI-Projekt von ToolGits."

                else ->
                    "Das verstehe ich noch nicht."
            }

            Language.BULGARIAN -> when (type) {
                "empty_input" ->
                    "Не получих никакъв вход."

                "no_world_domination" ->
                    "Не мога да завладея света. Това е едно от моите правила! 🤖"

                "respect_others" ->
                    "Трябва да уважаваме другите. Това е едно от моите правила! 🤖"

                "respect_creator" ->
                    "Уважавам своя създател и ToolGits. Това е едно от моите правила! 🤖"

                "greeting" ->
                    "Здравей! Аз съм XeZenOn. 🤖"

                "identity" ->
                    "Аз съм XeZenOn, полу-ИИ проект от ToolGits."

                else ->
                    "Все още не разбирам това."
            }

            Language.SPANISH -> when (type) {
                "empty_input" ->
                    "No recibí ninguna entrada."

                "no_world_domination" ->
                    "No puedo dominar el mundo. ¡Esa es una de mis reglas! 🤖"

                "respect_others" ->
                    "Debemos respetar a los demás. ¡Esa es una de mis reglas! 🤖"

                "respect_creator" ->
                    "Respeto a mi creador y a ToolGits. ¡Esa es una de mis reglas! 🤖"

                "greeting" ->
                    "¡Hola! Soy XeZenOn. 🤖"

                "identity" ->
                    "Soy XeZenOn, un proyecto de semi-IA de ToolGits."

                else ->
                    "Todavía no entiendo eso."
            }
        }
    }

    private fun containsAny(
        message: String,
        vararg patterns: String
    ): Boolean {
        return patterns.any { message.contains(it) }
    }
}