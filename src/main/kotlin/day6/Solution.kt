package day6

import java.io.BufferedReader
import kotlin.math.floor

class Solution {
    private val regex = """(\d+)""".toRegex()
    fun solve(resourcePath: String): Int {
        val inputStream = this::class.java.getResourceAsStream(resourcePath)!!
        val reader = inputStream.bufferedReader()
        val times = getListOfValues(reader.readLine())
        val distances = getListOfValues(reader.readLine())
        val strategies = mutableListOf<Int>()
        for ((index, time) in times.withIndex()) {
            var wins = 0;
            for (i in 1..time) {
                val runTime = time - i;
                val distance = runTime * i
                if (distance > distances[index]) {
                    wins++;
                }
            }
            strategies.add(wins)
        }
        return strategies.reduce {x, y ->  x * y}
    }

    fun solve2(resourcePath: String): Int {
        val inputStream = this::class.java.getResourceAsStream(resourcePath)!!
        val reader = inputStream.bufferedReader()
        val time = getValue(reader.readLine())
        val distance = getValue(reader.readLine())

        var strategies = 0

        for (i in 1..time) {
            print("${floor((i.toDouble()/time)*100)}%\r")
            val runTime = time - i;
            val d = runTime * i
            if (d > distance) {
                strategies++;
            }
        }
        return strategies
    }

    private fun getListOfValues(line: String) = regex.findAll(line)
        .map { result -> result.value.toInt() }
        .toList()

    private fun getValue(line: String) = regex.findAll(line)
        .map { result -> result.value }
        .joinToString("")
        .toLong()

}