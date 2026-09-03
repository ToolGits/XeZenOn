package com.toolgits.xezenon.boxhead

class KnowledgeBase {

    data class Fact(
        val subject: String,
        val relation: String,
        val value: String,
        val keywords: List<String> = emptyList()
    )

    private val facts =
        mutableListOf<Fact>()

    fun addFact(
        subject: String,
        relation: String,
        value: String,
        keywords: List<String> = emptyList()
    ) {
        val cleanSubject =
            subject.trim()

        val cleanRelation =
            relation.trim()

        val cleanValue =
            value.trim()

        val cleanKeywords =
            keywords
                .map { it.trim().lowercase() }
                .filter { it.isNotEmpty() }
                .distinct()

        if (
            cleanSubject.isEmpty() ||
            cleanRelation.isEmpty() ||
            cleanValue.isEmpty()
        ) {
            return
        }

        val fact = Fact(
            subject = cleanSubject,
            relation = cleanRelation,
            value = cleanValue,
            keywords = cleanKeywords
        )

        if (!facts.contains(fact)) {
            facts.add(fact)
        }
    }

    fun find(
        subject: String,
        relation: String? = null
    ): List<Fact> {

        val normalizedSubject =
            normalize(subject)

        val normalizedRelation =
            relation?.let {
                normalize(it)
            }

        return facts.filter { fact ->

            val subjectMatches =
                normalize(fact.subject) ==
                    normalizedSubject

            val relationMatches =
                normalizedRelation == null ||
                    normalize(fact.relation) ==
                    normalizedRelation

            subjectMatches &&
                relationMatches
        }
    }

    fun search(
        query: String
    ): List<Fact> {

        val queryWords =
            tokenize(query)

        if (queryWords.isEmpty()) {
            return emptyList()
        }

        return facts
            .map { fact ->
                fact to score(
                    fact,
                    queryWords
                )
            }
            .filter {
                it.second > 0
            }
            .sortedByDescending {
                it.second
            }
            .map {
                it.first
            }
    }

    fun all(): List<Fact> {
        return facts.toList()
    }

    fun size(): Int {
        return facts.size
    }

    fun clear() {
        facts.clear()
    }

    private fun score(
        fact: Fact,
        queryWords: List<String>
    ): Int {

        val subjectWords =
            tokenize(fact.subject)

        val relationWords =
            tokenize(fact.relation)

        val valueWords =
            tokenize(fact.value)

        val keywordWords =
            fact.keywords.flatMap {
                tokenize(it)
            }

        var score = 0

        for (word in queryWords) {

            if (subjectWords.contains(word)) {
                score += 10
            }

            if (relationWords.contains(word)) {
                score += 5
            }

            if (keywordWords.contains(word)) {
                score += 5
            }

            if (valueWords.contains(word)) {
                score += 1
            }
        }

        return score
    }

    private fun tokenize(
        input: String
    ): List<String> {

        return normalize(input)
            .replace(
                Regex("[^\\p{L}\\p{N}\\s]"),
                " "
            )
            .split(
                Regex("\\s+")
            )
            .filter {
                it.length >= 2
            }
            .distinct()
    }

    private fun normalize(
        input: String
    ): String {

        return input
            .trim()
            .lowercase()
            .replace(
                Regex("\\s+"),
                " "
            )
    }
}