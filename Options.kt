package search

enum class Options(val number: Int) {
    FIND(1),
    PRINT(2),
    EXIT(0);

    companion object {
        fun getOption(input: Int): Options {
            for (option in Options.values()) {
                if (option.number == input) return option
            }
            throw RuntimeException("Incorrect option! Try again.")
        }
    }
}