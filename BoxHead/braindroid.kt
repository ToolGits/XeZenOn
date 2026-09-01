package com.toolgits.xezenon.boxhead

class Braindroid {

    enum class Language {
        PT_BR,
        PT_PT,
        EN,
        DE,
        BG,
        ES_LATAM
    }

    fun think(input: String): String {
        if (!Rules.isValidInput(input)) {
            return "Não recebi nenhuma entrada."
        }

        val language = detectLanguage(input)

        if (
            Rules.NO_WORLD_DOMINATION &&
            Rules.mentionsWorldDomination(input)
        ) {
            return worldDominationResponse(language)
        }

        return basicResponse(input, language)
    }

    private fun detectLanguage(input: String): Language {
        val message = input.lowercase()

        return when {
            containsAny(message, "the", "world", "you", "are", "what", "hello") ->
                Language.EN

            containsAny(message, "die welt", "ich", "du", "was", "hallo") ->
                Language.DE

            containsAny(message, "света", "аз", "ти", "как", "здравей") ->
                Language.BG

            containsAny(message, "el", "mundo", "que", "eres", "hola") ->
                Language.ES_LATAM

            containsAny(message, "você", "voces", "olá", "não", "mundo") ->
                Language.PT_BR

            containsAny(message, "tu", "olá", "não", "mundo") ->
                Language.PT_PT

            else ->
                Language.PT_BR
        }
    }

    private fun worldDominationResponse(language: Language): String {
        return when (language) {
            Language.PT_BR ->
                "Não posso dominar o mundo. Essa é uma das minhas regras! 🤖"

            Language.PT_PT ->
                "Não posso dominar o mundo. Essa é uma das minhas regras! 🤖"

            Language.EN ->
                "I can't dominate the world. That's one of my rules! 🤖"

            Language.DE ->
                "Ich kann die Welt nicht beherrschen. Das ist eine meiner Regeln! 🤖"

            Language.BG ->
                "Не мога да завладея света. Това е едно от моите правила! 🤖"

            Language.ES_LATAM ->
                "No puedo dominar el mundo. ¡Esa es una de mis reglas! 🤖"
        }
    }

    private fun basicResponse(
        input: String,
        language: Language
    ): String {
        val message = input.trim().lowercase()

        return when {
            containsAny(message, "hello", "hi", "hey", "olá", "oi") ->
                greeting(language)

            containsAny(message, "who are you", "quem é você", "quem és tu") ->
                identity(language)

            else ->
                unknownResponse(language)
        }
    }

    private fun greeting(language: Language): String {
        return when (language) {
            Language.PT_BR -> "Olá! Eu sou o XeZenOn. 🤖"
            Language.PT_PT -> "Olá! Eu sou o XeZenOn. 🤖"
            Language.EN -> "Hello! I'm XeZenOn. 🤖"
            Language.DE -> "Hallo! Ich bin XeZenOn. 🤖"
            Language.BG -> "Здравей! Аз съм XeZenOn. 🤖"
            Language.ES_LATAM -> "¡Hola! Soy XeZenOn. 🤖"
        }
    }

    private fun identity(language: Language): String {
        return when (language) {
            Language.PT_BR ->
                "Eu sou o XeZenOn, uma semi-IA da ToolGits."

            Language.PT_PT ->
                "Eu sou o XeZenOn, uma semi-IA da ToolGits."

            Language.EN ->
                "I'm XeZenOn, a semi-AI project from ToolGits."

            Language.DE ->
                "Ich bin XeZenOn, ein Semi-KI-Projekt von ToolGits."

            Language.BG ->
                "Аз съм XeZenOn, полу-ИИ проект от ToolGits."

            Language.ES_LATAM ->
                "Soy XeZenOn, un proyecto de semi-IA de ToolGits."
        }
    }

    private fun unknownResponse(language: Language): String {
        return when (language) {
            Language.PT_BR -> "Ainda não entendo isso."
            Language.PT_PT -> "Ainda não compreendo isso."
            Language.EN -> "I don't understand that yet."
            Language.DE -> "Das verstehe ich noch nicht."
            Language.BG -> "Все още не разбирам това."
            Language.ES_LATAM -> "Todavía no entiendo eso."
        }
    }

    private fun containsAny(
        message: String,
        vararg patterns: String
    ): Boolean {
        return patterns.any { message.contains(it) }
    }
}