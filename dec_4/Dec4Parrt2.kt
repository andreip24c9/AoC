package dec_4

import utils.AocChallenge
import utils.forEachLineReversed
import utils.forEachLineReversedIndexed
import java.io.File

class Dec4Parrt2 : AocChallenge {
    override var result: String

    init {
        var sum = 0
        var winMap = mutableMapOf<Int, Int>()
        File("dec_4_test").forEachLineReversedIndexed { index, line ->
            val splitByColon = line.split(": ")
            val cardNo = splitByColon[0].split(" ")[1].toInt()
            val winningAndOwnArrays = splitByColon[1].split(" | ")

            val winningArray = winningAndOwnArrays[0].split(" ")
                .filterNot { it.isEmpty() }
                .map { it.toInt() }
                .toSet()

            val ownArray = winningAndOwnArrays[1].split(" ")
                .filterNot { it.isEmpty() }
                .map { it.toInt() }
                .toSet()

            var pow = 1
            var winningCount = winningArray.intersect(ownArray).size
            repeat(winningCount) { pow *= 2 }
            winMap[index] = if (pow > 1) pow / 2 else 0

            println("Card $cardNo: \t winning cards: ${if (winningCount > 0) winningCount else 0} \t total win: ${pow / 2}")
            var previousWinIndex = (index - winningCount)
            do {
                if (previousWinIndex > 0) {
                    println("-> Add winning from Card:[${converyConvert(previousWinIndex)}] win: ${winMap[previousWinIndex]!!} ${if(previousWinIndex == index) " (current)" else ""} ")
                    sum += winMap[previousWinIndex]!!
                }
                previousWinIndex += 1
            } while (previousWinIndex <= index)
            println()
//            sum += pow / 2
        }
        result = sum.toString()
    }

    private fun converyConvert(number: Int) : Int {
        return when(number) {
            5 -> 1
            4 -> 2
            3 -> 3
            2 -> 4
            1 -> 5
            0 -> 6
            else -> 123
        }
    }
}





/*

Card 6: 	 winning cards: 0 	 total win: 0

Card 5: 	 winning cards: 0 	 total win: 0
-> Add winning from Card:[5] win: 0  (current)

Card 4: 	 winning cards: 1 	 total win: 1
-> Add winning from Card:[5] win: 0
-> Add winning from Card:[4] win: 1  (current)

Card 3: 	 winning cards: 2 	 total win: 2
-> Add winning from Card:[5] win: 0
-> Add winning from Card:[4] win: 1
-> Add winning from Card:[3] win: 2  (current)

Card 2: 	 winning cards: 2 	 total win: 2
-> Add winning from Card:[4] win: 1
-> Add winning from Card:[3] win: 2
-> Add winning from Card:[2] win: 2  (current)

Card 1: 	 winning cards: 4 	 total win: 8
-> Add winning from Card:[5] win: 0
-> Add winning from Card:[4] win: 1
-> Add winning from Card:[3] win: 2
-> Add winning from Card:[2] win: 2
-> Add winning from Card:[1] win: 8  (current)

Result: 22*/
