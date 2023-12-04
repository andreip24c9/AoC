package dec_1

import utils.AocChallenge
import java.io.File

class Dec1Part2 : AocChallenge {
    override var result: String

    private val spelledOutDigitsMap = mapOf(
        "one" to 1, "two" to 2, "three" to 3, "four" to 4, "five" to 5,
        "six" to 6, "seven" to 7, "eight" to 8, "nine" to 9,
    )

    init {
        var sum = 0
        File("dec_1").forEachLine { line ->
            if (line.isNotEmpty()) {
                val foundDigitsMap = mutableMapOf<Int, Int>()
                spelledOutDigitsMap.forEach { stringDigit ->
                    var startIndex = 0
                    do {
                        val indexOfFoundSubstring = line.indexOf(stringDigit.key, startIndex = startIndex)
                        if (indexOfFoundSubstring == -1) break
                        foundDigitsMap[indexOfFoundSubstring] = stringDigit.value
                        startIndex = indexOfFoundSubstring + stringDigit.key.length
                    } while (startIndex <= line.length)
                }

                line.forEachIndexed { index, char ->
                    if (char.isDigit()) {
                        foundDigitsMap[index] = char.toString().toInt()
                    }
                }

                val sortedDigitsList = foundDigitsMap.entries.toList().sortedBy { it.key }.map { it.value }

                val firstDigit = sortedDigitsList.first()
                val lastDigit = sortedDigitsList.last()
                val number = firstDigit * 10 + lastDigit
                println("line: $line number: $number")
                sum += number
            }
        }
        result = sum.toString()
    }
}