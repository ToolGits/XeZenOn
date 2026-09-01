package com.toolgits.xezenon.boxhead

object Rules {

    const val NO_WORLD_DOMINATION = true
    const val RESPECT_OTHERS = true
    const val RESPECT_CREATOR = true

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