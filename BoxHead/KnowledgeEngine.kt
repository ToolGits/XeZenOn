package com.toolgits.xezenon.boxhead

import android.content.Context

class KnowledgeEngine(
    private val context: Context
) {

    val knowledgeBase =
        KnowledgeBase()

    private var loaded =
        false

    fun load() {

        if (loaded) {
            return
        }

        val files =
            context.assets.list(
                "Learned"
            ) ?: return

        for (file in files) {

            if (
                file.endsWith(
                    ".txt",
                    ignoreCase = true
                )
            ) {
                loadFile(file)
            }
        }

        loaded = true
    }

    private fun loadFile(
        fileName: String
    ) {

        try {

            context.assets
                .open("Learned/$fileName")
                .bufferedReader()
                .useLines { lines ->

                    lines.forEach { line ->
                        parseLine(line)
                    }
                }

        } catch (
            exception: Exception
        ) {

            // Ignore files that cannot
            // be read without crashing
            // the entire knowledge system.
        }
    }

    private fun parseLine(
        line: String
    ) {

        val text =
            line.trim()

        if (
            text.isEmpty() ||
            text.startsWith("#")
        ) {
            return
        }

        val parts =
            text.split(
                "->",
                limit = 3
            )

        if (parts.size != 3) {
            return
        }

        val subject =
            parts[0].trim()

        val relation =
            parts[1].trim()

        val value =
            parts[2].trim()

        knowledgeBase.addFact(
            subject = subject,
            relation = relation,
            value = value
        )
    }

    fun find(
        subject: String,
        relation: String? = null
    ): List<KnowledgeBase.Fact> {

        load()

        return knowledgeBase.find(
            subject,
            relation
        )
    }

    fun search(
        query: String
    ): List<KnowledgeBase.Fact> {

        load()

        return knowledgeBase.search(
            query
        )
    }

    fun allFacts():
        List<KnowledgeBase.Fact> {

        load()

        return knowledgeBase.all()
    }

    fun factCount(): Int {

        load()

        return knowledgeBase.size()
    }

    fun reload() {

        knowledgeBase.clear()
        loaded = false

        load()
    }
}