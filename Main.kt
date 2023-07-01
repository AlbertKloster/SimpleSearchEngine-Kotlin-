package search

fun main() {
    val words = readln().trim().split(Regex(" +"))
    val searchWord = readln()
    val foundIndex = words.indexOf(searchWord) + 1
    println(if (foundIndex < 1) "Not found" else foundIndex)
}
