package search

import java.io.File

fun main(args: Array<String>) {
    val people = getPeople(args)
    var exit = false
    while (!exit) {
        printMenu()
        while (true) {
            try {
                when (Options.getOption(readln().toInt())) {
                    Options.FIND -> findPerson(people)
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

private fun  findPerson(people: List<String>) {
    println("Enter a name or email to search all suitable people.")
    val data = readln()
    val foundPeople = people.filter { it.lowercase().matches(Regex(".*" + data.lowercase() + ".*")) }
    if (foundPeople.isEmpty()) {
        println("No matching people found.")
    } else {
        println(foundPeople.joinToString("\n"))
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

