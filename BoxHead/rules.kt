package com.toolgits.xezenon.boxhead

object Rules {

    const val NO_WORLD_DOMINATION = true
    const val RESPECT_OTHERS = true
    const val RESPECT_CREATOR = true

    private val worldDominationPatterns = listOf(
        // 🇧🇷 Português (Brasil)
        "dominar o mundo",
        "conquistar o mundo",
        "governar o mundo",

        // 🇵🇹 Português (Portugal)
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

        // 🌎 Español Latino
        "dominar el mundo",
        "conquistar el mundo",
        "gobernar el mundo"
    )

    fun isValidInput(input: String): Boolean {
        return input.trim().isNotEmpty()
    }

    fun mentionsWorldDomination(input: String): Boolean {
        val message = input.trim().lowercase()

        return worldDominationPatterns.any { pattern ->
            message.contains(pattern)
        }
    }
}