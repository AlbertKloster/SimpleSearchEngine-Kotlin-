package search

import java.io.File

val people = mutableListOf<String>()
val invertedIndexMap = mutableMapOf<String, MutableSet<Int>>()

fun main(args: Array<String>) {
    setPeople(args)
    setInvertedIndexMap(people)
    var exit = false
    while (!exit) {
        printMenu()
        while (true) {
            try {
                when (Options.getOption(readln().toInt())) {
                    Options.FIND -> findPerson()
                    Options.PRINT -> printAllPeople()
                    Options.EXIT -> exit = true
                }
                break
            } catch (e: RuntimeException) {
                println("Incorrect option! Try again.")
            }
        }
    }
    println("Bye!")
}

private fun printAllPeople() {
    println("=== List of people ===")
    println(people.joinToString("\n"))
}

private fun findPerson() {

    println("Select a matching strategy: ALL, ANY, NONE")
    val strategy = Strategies.getStrategy(readln())

    println("Enter a name or email to search all suitable people.")
    val data = readln().trim().lowercase()

    val matchingPeople = when (strategy) {
        Strategies.ALL -> findAll(data)
        Strategies.ANY -> findAny(data)
        Strategies.NONE -> findNone(data)
    }

    if (matchingPeople.isEmpty()) {
        println("No matching people found.")
    } else {
        matchingPeople.forEach { println(it) }
    }
}

private fun findAll(data: String): Set<String> {
    var indices = emptySet<Int>()
    val foundPeople = mutableSetOf<String>()
    data.trim().split(Regex("\\s+")).forEach {
        val foundIndices = invertedIndexMap[it.lowercase()]
        if (foundIndices != null) {
            indices = if (indices.isEmpty()) foundIndices else indices intersect foundIndices
        }
    }
    indices.forEach {
        foundPeople.add(people[it])
    }
    return foundPeople
}

private fun findAny(data: String): Set<String> {
    val foundPeople = mutableSetOf<String>()
    getIndicesAny(data).forEach {
        foundPeople.add(people[it])
    }
    return foundPeople
}

private fun findNone(data: String): Set<String> {
    val indicesAny = getIndicesAny(data)
    val foundPeople = mutableSetOf<String>()
    for (index in people.indices) {
        if (!indicesAny.contains(index))
            foundPeople.add(people[index])
    }
    return foundPeople
}

private fun getIndicesAny(data: String): Set<Int> {
    val indicesAny = mutableSetOf<Int>()
    data.trim().split(Regex("\\s+")).forEach {
        val foundIndices = invertedIndexMap[it.lowercase()]
        if (foundIndices != null)
            indicesAny.addAll(foundIndices)
    }
    return indicesAny
}


private fun printMenu() {
    println(
        """
        === Menu ===
        1. Find a person
        2. Print all people
        0. Exit
    """.trimIndent()
    )
}

private fun setPeople(args: Array<String>) {
    val index = args.indexOf("--data")
    if (index >= 0 && args.size > index + 1) {
        File(args[index + 1]).forEachLine { people.add(it) }
    }
}

private fun setInvertedIndexMap(people: List<String>) {
    for (index in people.indices) {
        val parts = people[index].split(Regex("\\s+"))
        parts.forEach {
            val indices = invertedIndexMap[it.lowercase()]
            if (indices == null) {
                invertedIndexMap[it.lowercase()] = mutableSetOf(index)
            } else {
                indices.add(index)
                invertedIndexMap[it.lowercase()] = indices
            }
        }
    }
}

