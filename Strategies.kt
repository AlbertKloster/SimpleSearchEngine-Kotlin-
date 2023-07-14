package search

enum class Strategies(val string: String) {

    ALL("ALL"), ANY("ANY"), NONE("NONE");

    companion object {
        fun getStrategy(input: String): Strategies {
            for (strategy in Strategies.values()) {
                if (strategy.string == input) return strategy
            }
            throw RuntimeException("Wrong strategy $input")
        }
    }

}