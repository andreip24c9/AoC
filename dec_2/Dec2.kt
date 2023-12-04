package dec_2

import utils.AocChallenge
import utils.forEachLine
import java.io.File

class Dec2 : AocChallenge {
    override var result: String

    private val cubeRuleMap = mapOf("red" to 12, "green" to 13, "blue" to 14)

    init {
        var correctLinesSum = 0
        File("dec_2").forEachLine { line ->
            val colonSplitString = line.split(": ")
            val gameNo = colonSplitString[0].split("Game ")[1]
            colonSplitString[1].split("; ", ", ").forEach { cubeColorCount ->
                val spaceSplitString = cubeColorCount.split(" ")
                val count = spaceSplitString[0]
                val color = spaceSplitString[1]

                if (count.toInt() > cubeRuleMap[color]!!.toInt()) {
                    return@forEachLine
                }
            }
            correctLinesSum += gameNo.toInt()
        }
        result = correctLinesSum.toString()
    }
}