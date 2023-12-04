package dec_1

import utils.AocChallenge
import utils.forEachLine
import java.io.File

class Dec1 : AocChallenge {
    override var result: String

    init {
        var sum = 0
        File("dec_1").forEachLine { line ->
            if (line.isNotEmpty()) {
                val firstDigit = line.first { it.isDigit() }.digitToInt()
                val secondDigit = line.last { it.isDigit() }.digitToInt()
                val number = firstDigit * 10 + secondDigit
                println("line: $line number: $number")
                sum += number
            }
        }
        result = sum.toString()
    }
}