package com.toolgits.xezenon.boxhead

import android.content.Context

class KnowledgeEngine(
    private val context: Context
) {

    val knowledgeBase =
        KnowledgeBase()

    private var loaded =
        false

    private var currentSubject =
        ""

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

        currentSubject =
            subjectFromFile(fileName)

        try {

            context.assets
                .open("Learned/$fileName")
                .bufferedReader()
                .useLines { lines ->

                    lines.forEach { line ->

                        parseLine(
                            line,
                            fileName
                        )
                    }
                }

        } catch (
            exception: Exception
        ) {
        }
    }

    private fun parseLine(
        line: String,
        fileName: String
    ) {

        val text =
            line.trim()

        if (
            text.isEmpty() ||
            text.startsWith("#")
        ) {
            return
        }

        if (text.contains("->")) {

            parseStructuredLine(text)

            return
        }

        if (
            text.equals(
                "NeLixk Learning",
                ignoreCase = true
            )
        ) {
            return
        }

        if (
            text.equals(
                "Creator",
                ignoreCase = true
            )
        ) {
            currentSubject =
                "Creator"

            return
        }

        if (
            text.length < 3
        ) {
            return
        }

        val subject =
            if (currentSubject.isNotEmpty()) {
                currentSubject
            } else {
                subjectFromFile(fileName)
            }

        knowledgeBase.addFact(
            subject = subject,
            relation = "knowledge",
            value = text,
            keywords = extractKeywords(
                text
            )
        )
    }

    private fun parseStructuredLine(
        text: String
    ) {

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
            value = value,
            keywords = extractKeywords(
                "$subject $relation $value"
            )
        )
    }

    private fun subjectFromFile(
        fileName: String
    ): String {

        return fileName
            .substringBeforeLast(".")
            .replace("-", " ")
            .replace("_", " ")
            .trim()
    }

    private fun extractKeywords(
        text: String
    ): List<String> {

        return text
            .lowercase()
            .replace(
                Regex("[^\\p{L}\\p{N}\\s]"),
                " "
            )
            .split(
                Regex("\\s+")
            )
            .filter {
                it.length >= 3
            }
            .distinct()
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
        currentSubject = ""

        load()
    }
}