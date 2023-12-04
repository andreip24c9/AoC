package utils

import java.io.File

fun File.forEachLine(callback: (String) -> Unit) {
    println("File path: ${this.canonicalPath}")
    readLines().forEach(callback)
}

fun File.forEachLineIndexed(callback: (Int, String) -> Unit) {
    readLines().forEachIndexed { index, value -> callback(index, value) }
}