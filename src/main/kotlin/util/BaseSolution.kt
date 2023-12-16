package util

import java.io.BufferedReader

abstract class BaseSolution(resourcePath: String) {
    protected val reader: BufferedReader
    init {
        val inputStream = this::class.java.getResourceAsStream(resourcePath)!!
        reader = inputStream.bufferedReader()
    }

}