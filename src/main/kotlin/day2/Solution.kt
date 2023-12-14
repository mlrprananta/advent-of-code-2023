package day2

val CONFIG = mapOf(
    "red" to 12,
    "green" to 13,
    "blue" to 14
)

class Solution {
    private val inputStream = this::class.java.getResourceAsStream("/day2/input1")!!

    fun solve(): Int {
        val lines = inputStream.bufferedReader().readLines();
        val groupIdRegex = """Game (?<groupId>\d+):""".toRegex();
        var sum = 0;
        for (line in lines) {
            val groupId = groupIdRegex.find(line)!!.groups["groupId"]!!.value.toInt();
            if (isPossible(line)) {
                sum += groupId
            }
        }
        return sum;
    }

    fun solve2(): Int {
        val lines = inputStream.bufferedReader().readLines();
        var sum = 0;
        for (line in lines) {
            sum += extractPower(line);
        }
        return sum;
    }

    private fun extractPower(line: String): Int {
        val subsetRegex = """(((\d+ (red|green|blue)),? ?){1,3};*)""".toRegex();
        val sampleRegex = """(?<observations>\d+) (?<color>red|green|blue)""".toRegex();
        var cubes = mutableMapOf(
            "red" to 0,
            "green" to 0,
            "blue" to 0,
        )
        for (subsetMatchResult in subsetRegex.findAll(line)) {
            for (sampleMatchResult in sampleRegex.findAll(subsetMatchResult.value)) {
                val color = sampleMatchResult.groups["color"]!!.value
                val observations = sampleMatchResult.groups["observations"]!!.value.toInt()
                if (observations > cubes[color]!!) {
                    cubes[color] = observations
                }
            }
        }
        return cubes.values.reduce { x, y -> x * y }
    }

    private fun isPossible(line: String): Boolean {
        val subsetRegex = """(((\d+ (red|green|blue)),? ?){1,3};*)""".toRegex();
        val sampleRegex = """(?<observations>\d+) (?<color>red|green|blue)""".toRegex();
        for (subsetMatchResult in subsetRegex.findAll(line)) {
            for (sampleMatchResult in sampleRegex.findAll(subsetMatchResult.value)) {
                val color = sampleMatchResult.groups["color"]!!.value
                val observations = sampleMatchResult.groups["observations"]!!.value.toInt()
                if (observations > CONFIG[color]!!) {
                    return false
                }
            }
        }
        return true
    }


}