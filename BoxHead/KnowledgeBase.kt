package com.toolgits.xezenon.boxhead

class KnowledgeBase {

    data class Fact(
        val subject: String,
        val relation: String,
        val value: String
    )

    private val facts =
        mutableListOf<Fact>()

    fun addFact(
        subject: String,
        relation: String,
        value: String
    ) {
        val cleanSubject =
            subject.trim()

        val cleanRelation =
            relation.trim()

        val cleanValue =
            value.trim()

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
            value = cleanValue
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

        val normalizedQuery =
            normalize(query)

        if (normalizedQuery.isEmpty()) {
            return emptyList()
        }

        return facts.filter { fact ->

            normalize(fact.subject)
                .contains(normalizedQuery) ||

            normalize(fact.relation)
                .contains(normalizedQuery) ||

            normalize(fact.value)
                .contains(normalizedQuery)
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