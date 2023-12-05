package dec_3

import utils.AocChallenge
import utils.forEachLineIndexed
import java.io.File

class Dec3 : AocChallenge {
    override var result: String

    data class PartNumber(
        val number: Int,
        val startIndex: Int,
        val endIndex: Int
    )

    private val potentialPartNumbers = mutableMapOf<Int, MutableList<PartNumber>>()

    init {
        val validPartNumbers = mutableListOf<PartNumber>()
        var lineNumbers = 0

        File("dec_3").forEachLineIndexed { lineIndex, line ->
            var digitCounter = 0
            var lastFoundDigitIndex = 0
            val charsInLine = line.toCharArray()
            charsInLine.forEachIndexed { charIndex, char ->
                if (char.isDigit()) {
                    lastFoundDigitIndex = charIndex
                    digitCounter += 1
                } else {
                    findNumberBeforeSymbol(charsInLine, lastFoundDigitIndex, digitCounter, lineIndex)
                    digitCounter = 0
                }

                if(charIndex == charsInLine.size - 1) {
                    findNumberBeforeSymbol(charsInLine, lastFoundDigitIndex, digitCounter, lineIndex)
                    digitCounter = 0
                }
            }
            lineNumbers += 1
        }

        File("dec_3").forEachLineIndexed { lineIndex, line ->
            val charsInLine = line.toCharArray()
            charsInLine.forEachIndexed { specialCharIndex, char ->
                if (!char.isDigit() && char != '.') {
                    if (lineIndex - 1 >= 0) {
                        findPartNumberAround(
                            lineIndex = lineIndex - 1,
                            charIndex = specialCharIndex
                        ) { validPartNumbers.addAll(it) }
                    }

                    findPartNumberAround(
                        lineIndex = lineIndex,
                        charIndex = specialCharIndex
                    ) { validPartNumbers.addAll(it) }

                    if (lineIndex + 1 <= lineNumbers) {
                        findPartNumberAround(
                            lineIndex = lineIndex + 1,
                            charIndex = specialCharIndex
                        ) { validPartNumbers.addAll(it) }
                    }
                }
            }
        }

        println("validPartNumbers ${validPartNumbers.map { it.number }}")
        result = validPartNumbers.map { it.number }.reduce { acc, partNumber -> acc + partNumber }.toString()
    }

    private fun findPartNumberAround(lineIndex: Int, charIndex: Int, callback: (List<PartNumber>) -> Unit) {
        potentialPartNumbers[lineIndex]?.filter { potentialPartNumber ->
            (charIndex >= potentialPartNumber.startIndex && charIndex <= potentialPartNumber.startIndex) ||
                    (charIndex <= potentialPartNumber.endIndex + 1 && charIndex > potentialPartNumber.startIndex) ||
                    (charIndex >= potentialPartNumber.startIndex - 1 && charIndex < potentialPartNumber.endIndex)
        }?.let { callback(it) }
    }

    private fun findNumberBeforeSymbol(
        charsInLine: CharArray,
        lastFoundDigitIndex: Int,
        digitCounter: Int,
        lineIndex: Int
    ) {
        var number = ""
        var localDigitCounter = digitCounter
        val startIndex = lastFoundDigitIndex - localDigitCounter + 1
        val endIndex = lastFoundDigitIndex
        while (localDigitCounter > 0) {
            number += charsInLine[lastFoundDigitIndex - localDigitCounter + 1]
            localDigitCounter -= 1
        }

        if (number.isNotEmpty()) {
            val potentialPartNumber = PartNumber(
                number = number.toInt(),
                startIndex = startIndex,
                endIndex = endIndex
            )

            if (potentialPartNumbers[lineIndex] == null) {
                potentialPartNumbers[lineIndex] = mutableListOf()
            }
            potentialPartNumbers[lineIndex]!!.add(potentialPartNumber)
        }
    }
}