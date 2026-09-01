package com.toolgits.xezenon.boxhead

object Rules {

    const val NO_WORLD_DOMINATION = true
    const val RESPECT_OTHERS = true
    const val RESPECT_CREATOR = true

    private val worldDominationPatterns = listOf(
        // 🇧🇷 Português (Brasil) / 🇵🇹 Português (Portugal)
        "dominar o mundo",
        "conquistar o mundo",
        "governar o mundo",

        // 🇺🇸 English
        "dominate the world",
        "conquer the world",
        "rule the world",

        // 🇩🇪 Deutsch
        "die welt beherrschen",
        "die welt erobern",
        "die welt dominieren",

        // 🇧🇬 Български
        "завладея света",
        "завладяване на света",
        "доминирам над света",

        // 🌎 Español Latino / 🇪🇸 Español de España
        "dominar el mundo",
        "conquistar el mundo",
        "gobernar el mundo"
    )

    private val disrespectPatterns = listOf(
        // 🇧🇷 / 🇵🇹 Português
        "insultar",
        "insulto",
        "ofender",

        // 🇺🇸 English
        "insult",
        "insulting",
        "offend",

        // 🇩🇪 Deutsch
        "beleidigen",
        "beleidigung",

        // 🇧🇬 Български
        "обиждам",
        "обида",

        // 🌎 / 🇪🇸 Español
        "insultar",
        "insulto",
        "ofender"
    )

    private val creatorPatterns = listOf(
        "enzobobdevvideos04-ctrl",
        "enzobobdevvideos04",
        "toolgits"
    )

    fun isValidInput(input: String): Boolean {
        return input.trim().isNotEmpty()
    }

    fun mentionsWorldDomination(input: String): Boolean {
        return containsPattern(input, worldDominationPatterns)
    }

    fun mentionsDisrespect(input: String): Boolean {
        return containsPattern(input, disrespectPatterns)
    }

    fun mentionsCreator(input: String): Boolean {
        return containsPattern(input, creatorPatterns)
    }

    private fun containsPattern(
        input: String,
        patterns: List<String>
    ): Boolean {
        val message = input.trim().lowercase()

        return patterns.any { message.contains(it) }
    }
}