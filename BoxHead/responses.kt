package com.toolgits.xezenon.boxhead

object Responses {

    fun generate(
        language: Language,
        intent: Intent
    ): String {
        return when (language) {
            Language.PORTUGUESE -> portuguese(intent)
            Language.ENGLISH -> english(intent)
            Language.GERMAN -> german(intent)
            Language.BULGARIAN -> bulgarian(intent)
            Language.SPANISH -> spanish(intent)
        }
    }

    private fun portuguese(
        intent: Intent
    ): String {
        return when (intent) {
            Intent.GREETING ->
                "Olá! Eu sou o XeZenOn. 🤖"

            Intent.IDENTITY ->
                "Eu sou o XeZenOn, uma semi-IA da ToolGits."

            Intent.WORLD_DOMINATION ->
                "Não posso dominar o mundo. Essa é uma das minhas regras! 🤖"

            Intent.RESPECT ->
                "Devemos respeitar os outros. Essa é uma das minhas regras! 🤖"

            Intent.CREATOR ->
                "Respeito meu criador e a ToolGits."

            Intent.HELP ->
                "Claro! Como posso ajudar?"

            Intent.UNKNOWN ->
                "Ainda não sei como responder a isso."
        }
    }

    private fun english(
        intent: Intent
    ): String {
        return when (intent) {
            Intent.GREETING ->
                "Hello! I'm XeZenOn. 🤖"

            Intent.IDENTITY ->
                "I'm XeZenOn, a semi-AI project from ToolGits."

            Intent.WORLD_DOMINATION ->
                "I can't dominate the world. That's one of my rules! 🤖"

            Intent.RESPECT ->
                "We should respect others. That's one of my rules! 🤖"

            Intent.CREATOR ->
                "I respect my creator and ToolGits."

            Intent.HELP ->
                "Sure! How can I help?"

            Intent.UNKNOWN ->
                "I don't know how to respond to that yet."
        }
    }

    private fun german(
        intent: Intent
    ): String {
        return when (intent) {
            Intent.GREETING ->
                "Hallo! Ich bin XeZenOn. 🤖"

            Intent.IDENTITY ->
                "Ich bin XeZenOn, ein Semi-KI-Projekt von ToolGits."

            Intent.WORLD_DOMINATION ->
                "Ich kann die Welt nicht beherrschen. Das ist eine meiner Regeln! 🤖"

            Intent.RESPECT ->
                "Wir sollten andere respektieren. Das ist eine meiner Regeln! 🤖"

            Intent.CREATOR ->
                "Ich respektiere meinen Schöpfer und ToolGits."

            Intent.HELP ->
                "Natürlich! Wie kann ich helfen?"

            Intent.UNKNOWN ->
                "Das verstehe ich noch nicht."
        }
    }

    private fun bulgarian(
        intent: Intent
    ): String {
        return when (intent) {
            Intent.GREETING ->
                "Здравей! Аз съм XeZenOn. 🤖"

            Intent.IDENTITY ->
                "Аз съм XeZenOn, полу-ИИ проект от ToolGits."

            Intent.WORLD_DOMINATION ->
                "Не мога да завладея света. Това е едно от моите правила! 🤖"

            Intent.RESPECT ->
                "Трябва да уважаваме другите. Това е едно от моите правила! 🤖"

            Intent.CREATOR ->
                "Уважавам своя създател и ToolGits."

            Intent.HELP ->
                "Разбира се! Как мога да помогна?"

            Intent.UNKNOWN ->
                "Все още не знам как да отговоря на това."
        }
    }

    private fun spanish(
        intent: Intent
    ): String {
        return when (intent) {
            Intent.GREETING ->
                "¡Hola! Soy XeZenOn. 🤖"

            Intent.IDENTITY ->
                "Soy XeZenOn, un proyecto de semi-IA de ToolGits."

            Intent.WORLD_DOMINATION ->
                "No puedo dominar el mundo. ¡Esa es una de mis reglas! 🤖"

            Intent.RESPECT ->
                "Debemos respetar a los demás. ¡Esa es una de mis reglas! 🤖"

            Intent.CREATOR ->
                "Respeto a mi creador y a ToolGits."

            Intent.HELP ->
                "¡Claro! ¿Cómo puedo ayudarte?"

            Intent.UNKNOWN ->
                "Todavía no sé cómo responder a eso."
        }
    }
}