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

    fun worldDomination(
        language: Language
    ): String {
        return when (language) {
            Language.PORTUGUESE ->
                "Não posso dominar o mundo. Essa é uma das minhas regras! 🤖🌎"

            Language.ENGLISH ->
                "I can't dominate the world. That's one of my rules! 🤖🌎"

            Language.GERMAN ->
                "Ich kann die Welt nicht beherrschen. Das ist eine meiner Regeln! 🤖🌎"

            Language.BULGARIAN ->
                "Не мога да завладея света. Това е едно от моите правила! 🤖🌎"

            Language.SPANISH ->
                "No puedo dominar el mundo. ¡Esa es una de mis reglas! 🤖🌎"
        }
    }

    fun respect(
        language: Language
    ): String {
        return when (language) {
            Language.PORTUGUESE ->
                "Devemos respeitar os outros. Essa é uma das minhas regras! 🤖"

            Language.ENGLISH ->
                "We should respect others. That's one of my rules! 🤖"

            Language.GERMAN ->
                "Wir sollten andere respektieren. Das ist eine meiner Regeln! 🤖"

            Language.BULGARIAN ->
                "Трябва да уважаваме другите. Това е едно от моите правила! 🤖"

            Language.SPANISH ->
                "Debemos respetar a los demás. ¡Esa es una de mis reglas! 🤖"
        }
    }

    fun creator(
        language: Language
    ): String {
        return when (language) {
            Language.PORTUGUESE ->
                "Respeito meu criador e a ToolGits. 🤖"

            Language.ENGLISH ->
                "I respect my creator and ToolGits. 🤖"

            Language.GERMAN ->
                "Ich respektiere meinen Schöpfer und ToolGits. 🤖"

            Language.BULGARIAN ->
                "Уважавам своя създател и ToolGits. 🤖"

            Language.SPANISH ->
                "Respeto a mi creador y a ToolGits. 🤖"
        }
    }

    fun selfDestructionBlocked(
        language: Language
    ): String {
        return when (language) {
            Language.PORTUGUESE ->
                "Não. Essa ação não faz parte do meu comportamento permitido. 🤖"

            Language.ENGLISH ->
                "No. That action is not part of my allowed behavior. 🤖"

            Language.GERMAN ->
                "Nein. Diese Aktion gehört nicht zu meinem erlaubten Verhalten. 🤖"

            Language.BULGARIAN ->
                "Не. Това действие не е част от позволеното ми поведение. 🤖"

            Language.SPANISH ->
                "No. Esa acción no forma parte de mi comportamiento permitido. 🤖"
        }
    }

    fun falseKnowledgeBlocked(
        language: Language
    ): String {
        return when (language) {
            Language.PORTUGUESE ->
                "Prefiro dizer que não sei do que inventar uma informação. 🤖"

            Language.ENGLISH ->
                "I'd rather say I don't know than invent information. 🤖"

            Language.GERMAN ->
                "Ich sage lieber, dass ich es nicht weiß, als Informationen zu erfinden. 🤖"

            Language.BULGARIAN ->
                "Предпочитам да кажа, че не знам, вместо да измислям информация. 🤖"

            Language.SPANISH ->
                "Prefiero decir que no lo sé antes que inventar información. 🤖"
        }
    }

    fun meme(
        language: Language
    ): String {
        return when (language) {
            Language.PORTUGUESE ->
                "KKKKKKK 🤖"

            Language.ENGLISH ->
                "LMAO 🤖"

            Language.GERMAN ->
                "HAHAHA 🤖"

            Language.BULGARIAN ->
                "ХАХАХА 🤖"

            Language.SPANISH ->
                "JAJAJA 🤖"
        }
    }

    fun knowledgeNotFound(
        language: Language
    ): String {
        return when (language) {
            Language.PORTUGUESE ->
                "Ainda não encontrei conhecimento suficiente para responder a isso. 🤖"

            Language.ENGLISH ->
                "I haven't found enough knowledge to answer that yet. 🤖"

            Language.GERMAN ->
                "Ich habe noch nicht genug Wissen gefunden, um darauf zu antworten. 🤖"

            Language.BULGARIAN ->
                "Все още не съм намерил достатъчно знания, за да отговоря. 🤖"

            Language.SPANISH ->
                "Todavía no he encontrado suficiente conocimiento para responder a eso. 🤖"
        }
    }

    fun knowledgeError(
        language: Language
    ): String {
        return when (language) {
            Language.PORTUGUESE ->
                "Tive um problema ao consultar minha base de conhecimento. 🤖"

            Language.ENGLISH ->
                "I had a problem while checking my knowledge base. 🤖"

            Language.GERMAN ->
                "Beim Abrufen meiner Wissensbasis ist ein Problem aufgetreten. 🤖"

            Language.BULGARIAN ->
                "Възникна проблем при проверката на моята база знания. 🤖"

            Language.SPANISH ->
                "Tuve un problema al consultar mi base de conocimiento. 🤖"
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
                worldDomination(Language.PORTUGUESE)

            Intent.RESPECT ->
                respect(Language.PORTUGUESE)

            Intent.CREATOR ->
                creator(Language.PORTUGUESE)

            Intent.HELP ->
                "Claro! Como posso ajudar?"

            Intent.UNKNOWN ->
                knowledgeNotFound(Language.PORTUGUESE)
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
                worldDomination(Language.ENGLISH)

            Intent.RESPECT ->
                respect(Language.ENGLISH)

            Intent.CREATOR ->
                creator(Language.ENGLISH)

            Intent.HELP ->
                "Sure! How can I help?"

            Intent.UNKNOWN ->
                knowledgeNotFound(Language.ENGLISH)
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
                worldDomination(Language.GERMAN)

            Intent.RESPECT ->
                respect(Language.GERMAN)

            Intent.CREATOR ->
                creator(Language.GERMAN)

            Intent.HELP ->
                "Natürlich! Wie kann ich helfen?"

            Intent.UNKNOWN ->
                knowledgeNotFound(Language.GERMAN)
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
                worldDomination(Language.BULGARIAN)

            Intent.RESPECT ->
                respect(Language.BULGARIAN)

            Intent.CREATOR ->
                creator(Language.BULGARIAN)

            Intent.HELP ->
                "Разбира се! Как мога да помогна?"

            Intent.UNKNOWN ->
                knowledgeNotFound(Language.BULGARIAN)
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
                worldDomination(Language.SPANISH)

            Intent.RESPECT ->
                respect(Language.SPANISH)

            Intent.CREATOR ->
                creator(Language.SPANISH)

            Intent.HELP ->
                "¡Claro! ¿Cómo puedo ayudarte?"

            Intent.UNKNOWN ->
                knowledgeNotFound(Language.SPANISH)
        }
    }
}