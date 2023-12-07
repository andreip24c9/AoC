package dec_4

import utils.AocChallenge
import java.io.File

class Dec4 : AocChallenge {
    override var result: String

    init {
        var sum = 0
        File("dec_4").forEachLine { line ->
            val winningAndOwnArrays = line.split(": ")[1].split(" | ")

            val winningArray = winningAndOwnArrays[0].split(" ")
                .filterNot { it.isEmpty() }
                .map { it.toInt() }
                .toSet()

            val ownArray = winningAndOwnArrays[1].split(" ")
                .filterNot { it.isEmpty() }
                .map { it.toInt() }
                .toSet()

            var pow = 1
            repeat(winningArray.intersect(ownArray).size) { pow *= 2 }
            sum += pow / 2
        }
        result = sum.toString()
    }
}