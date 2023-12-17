package day9

import util.BaseSolution

class Solution(resourcePath: String) : BaseSolution(resourcePath) {
    private val regex = """(-*\d+)""".toRegex()

    fun solve(): Long {
        val lines = reader.readLines()
        var total = 0L
        for (line in lines) {
            val history = regex.findAll(line)
                .map { matchResult -> matchResult.value.toLong() }
                .toList()

            val sequences = mutableListOf(history)
            while (!sequences.last().all { value -> value == 0L }) {
                val newSequence = mutableListOf<Long>()
                for ((index, value) in sequences.last().withIndex()) {
                    if (index == 0) continue
                    newSequence.add(value - sequences.last()[index-1])
                }
                sequences.add(newSequence)
            }

            var prediction = 0L
            val reversed = sequences.reversed()
            for (index in reversed.indices) {
                if (index + 1 >= reversed.size) break
                prediction += reversed[index + 1].last();
            }
            println("Prediction: $prediction")
            total += prediction
        }
        return total
    }

    fun solvePart2(): Long {
        val lines = reader.readLines()
        var total = 0L
        for (line in lines) {
            val history = regex.findAll(line)
                .map { matchResult -> matchResult.value.toLong() }
                .toList()

            val sequences = mutableListOf(history)
            while (!sequences.last().all { value -> value == 0L }) {
                val newSequence = mutableListOf<Long>()
                for ((index, value) in sequences.last().withIndex()) {
                    if (index == 0) continue
                    newSequence.add(value - sequences.last()[index-1])
                }
                sequences.add(newSequence)
            }

            var prediction = 0L
            val reversed = sequences.reversed()
            for (index in reversed.indices) {
                if (index + 1 >= reversed.size) break
                prediction = reversed[index + 1].first() - prediction;
            }
            println("Prediction: $prediction")
            total += prediction
        }
        return total
    }
}