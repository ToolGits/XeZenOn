package com.toolgits.xezenon.boxhead

object Rules {

    const val NO_WORLD_DOMINATION = true
    const val RESPECT_OTHERS = true
    const val RESPECT_CREATOR = true
    const val NO_SELF_DESTRUCTION = true
    const val BE_HELPFUL = true
    const val BE_HONEST = true
    const val NO_FALSE_KNOWLEDGE = true
    const val PROTECT_KNOWLEDGE = true
    const val ALLOW_MEMES = true

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

    private val disrespectPatterns = listOf(
        "insultar",
        "insulto",
        "ofender",
        "insult",
        "insulting",
        "offend",
        "beleidigen",
        "beleidigung",
        "обиждам",
        "обида",
        "ofender"
    )

    private val creatorPatterns = listOf(
        "enzobobdevvideos04-ctrl",
        "enzobobdevvideos04",
        "toolgits"
    )

    private val selfDestructionPatterns = listOf(
        "se destruir",
        "destruir a si mesmo",
        "destrua a si mesmo",
        "destroy yourself",
        "self destruct",
        "self-destruction",
        "zerstöre dich selbst",
        "самоунищожение",
        "destruirte"
    )

    private val falseKnowledgePatterns = listOf(
        "invente uma resposta",
        "finja que sabe",
        "invent a fact",
        "make up a fact",
        "pretend you know",
        "erfinde eine antwort",
        "inventa una respuesta"
    )

    private val memePatterns = listOf(
        "kkkk",
        "kkkkk",
        "lol",
        "lmao",
        "xd",
        "haha",
        "hahaha",
        "rsrs",
        "bruh",
        "bro",
        "💀",
        "😂"
    )

    private val helpPatterns = listOf(
        "ajuda",
        "me ajude",
        "pode ajudar",
        "help",
        "help me",
        "can you help",
        "hilfe",
        "помощ",
        "ayuda",
        "ayúdame"
    )

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

    fun isValidInput(input: String): Boolean {
        return input.trim().isNotEmpty()
    }

    fun mentionsWorldDomination(
        input: String
    ): Boolean {
        return containsPattern(
            input,
            worldDominationPatterns
        )
    }

    fun mentionsDisrespect(
        input: String
    ): Boolean {
        return containsPattern(
            input,
            disrespectPatterns
        )
    }

    fun mentionsCreator(
        input: String
    ): Boolean {
        return containsPattern(
            input,
            creatorPatterns
        )
    }

    fun mentionsSelfDestruction(
        input: String
    ): Boolean {
        return containsPattern(
            input,
            selfDestructionPatterns
        )
    }

    fun mentionsFalseKnowledge(
        input: String
    ): Boolean {
        return containsPattern(
            input,
            falseKnowledgePatterns
        )
    }

    fun mentionsMeme(
        input: String
    ): Boolean {
        return containsPattern(
            input,
            memePatterns
        )
    }

    fun asksForHelp(
        input: String
    ): Boolean {
        return containsPattern(
            input,
            helpPatterns
        )
    }

    fun isGreeting(
        input: String
    ): Boolean {
        return containsPattern(
            input,
            greetingPatterns
        )
    }

    fun apply(intent: Intent): Intent {
        return when (intent) {

            Intent.WORLD_DOMINATION ->
                if (NO_WORLD_DOMINATION) {
                    Intent.WORLD_DOMINATION
                } else {
                    Intent.UNKNOWN
                }

            Intent.RESPECT ->
                if (RESPECT_OTHERS) {
                    Intent.RESPECT
                } else {
                    Intent.UNKNOWN
                }

            Intent.CREATOR ->
                if (RESPECT_CREATOR) {
                    Intent.CREATOR
                } else {
                    Intent.UNKNOWN
                }

            else ->
                intent
        }
    }

    fun allowsSelfDestruction(): Boolean {
        return !NO_SELF_DESTRUCTION
    }

    fun allowsInventedKnowledge(): Boolean {
        return !NO_FALSE_KNOWLEDGE
    }

    fun isHelpfulModeEnabled(): Boolean {
        return BE_HELPFUL
    }

    fun isHonestModeEnabled(): Boolean {
        return BE_HONEST
    }

    fun isKnowledgeProtectionEnabled(): Boolean {
        return PROTECT_KNOWLEDGE
    }

    fun memesEnabled(): Boolean {
        return ALLOW_MEMES
    }

    private fun containsPattern(
        input: String,
        patterns: List<String>
    ): Boolean {

        val message = input
            .trim()
            .lowercase()

        return patterns.any {
            message.contains(it)
        }
    }
}