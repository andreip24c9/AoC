package dec_2

import utils.AocChallenge
import utils.forEachLine
import java.io.File

class Dec2Part2 : AocChallenge {
    override var result: String

    init {
        var correctLinesSum = 0
        File("dec_2").forEachLine { line ->
            val colonSplitString = line.split(": ")
            val countColorMap = mutableMapOf<String, Int>()
            colonSplitString[1].split("; ", ", ").forEach { cubeColorCount ->
                val spaceSplitString = cubeColorCount.split(" ")
                val count = spaceSplitString[0]
                val color = spaceSplitString[1]

                if (!countColorMap.containsKey(color) || countColorMap[color]!! < count.toInt()) {
                    countColorMap[color] = count.toInt()
                }
            }

            var cubeNumberPowers = 1
            countColorMap.forEach { cubeNumberPowers *= it.value }
            println("$line\nCountColorMap: $countColorMap\nCubeNumberPowers $cubeNumberPowers\n")
            correctLinesSum += cubeNumberPowers
        }
        result = correctLinesSum.toString()
    }
}