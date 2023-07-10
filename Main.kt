package search

import java.io.File

fun main(args: Array<String>) {
    val people = getPeople(args)
    val invertedIndexMap = getInvertedIndexMap(people)
    var exit = false
    while (!exit) {
        printMenu()
        while (true) {
            try {
                when (Options.getOption(readln().toInt())) {
                    Options.FIND -> findPerson(people, invertedIndexMap)
                    Options.PRINT -> printAllPeople(people)
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

private fun printAllPeople(people: List<String>) {
    println("=== List of people ===")
    println(people.joinToString("\n"))
}

private fun  findPerson(people: List<String>, invertedIndexMap: Map<String, List<Int>>) {
    println("Enter a name or email to search all suitable people.")
    val data = readln().trim().lowercase()

    val indices = invertedIndexMap[data]

    if (indices == null) {
        println("No matching people found.")
    } else {
        indices.forEach { println(people[it]) }
    }
}

private fun printMenu() {
    println("""
        === Menu ===
        1. Find a person
        2. Print all people
        0. Exit
    """.trimIndent())
}

private fun getPeople(args: Array<String>): List<String> {
    val people = mutableListOf<String>()
    val index = args.indexOf("--data")
    if (index >= 0 && args.size > index + 1) {
        File(args[index + 1]).forEachLine { people.add(it) }
    }
    return people
}

private fun getInvertedIndexMap(people: List<String>): Map<String, List<Int>> {
    val invertedIndexMap = mutableMapOf<String, MutableList<Int>>()
    for (index in people.indices) {
        val parts = people[index].split(Regex("\\s+"))
        parts.forEach {
            val indices = invertedIndexMap[it.lowercase()]
            if (indices == null) {
                invertedIndexMap[it.lowercase()] = mutableListOf(index)
            } else {
                if (!indices.contains(index)) {
                    indices.add(index)
                    invertedIndexMap[it.lowercase()] = indices
                }
            }
        }
    }
    return invertedIndexMap
}

