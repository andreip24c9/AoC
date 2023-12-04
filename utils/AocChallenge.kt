package utils

interface AocChallenge {
    var result: String

    fun printResult() {
        println("Result: $result")
    }
}