package search

fun main() {
    println("Enter the number of people:")
    val numberOfPeople = readln().toInt()
    println("Enter all people:")
    val people = List(numberOfPeople) { readln() }

    println("Enter the number of search queries:")
    val numberOfSearchQueries = readln().toInt()

    for (i in 1..numberOfSearchQueries) {
        println("Enter data to search people:")
        val data = readln()

        val foundPeople = people.filter { it.lowercase().matches(Regex(".*" + data.lowercase() + ".*")) }

        if (foundPeople.isEmpty()) {
            println("No matching people found.")
        } else {
            println("People found:")
            println(foundPeople.joinToString("\n"))
        }

    }
}
