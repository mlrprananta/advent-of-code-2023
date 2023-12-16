package day8

import util.BaseSolution

class Solution(resourcePath: String) : BaseSolution(resourcePath) {
    private val regex = """(?<node>\w+) = \((?<left>\w+), (?<right>\w+)\)""".toRegex()

    fun solve(): Int {
        val path = reader.readLine().toCharArray().toList()
        val neighbours = hashMapOf<String, List<String>>()
        var currentNode = "AAA"
        var steps = 0

        // Skip empty line
        reader.readLine()

        while(true) {
            val line = reader.readLine()?: break
            val matchResult = regex.find(line)!!
            val node = matchResult.groups["node"]!!.value
            val left = matchResult.groups["left"]!!.value
            val right = matchResult.groups["right"]!!.value
            neighbours[node] = listOf(left, right)
        }

        while (currentNode != "ZZZ") {
            val direction = if (path[steps % path.size] == 'L') 0 else 1
            currentNode = neighbours[currentNode]!![direction]
            steps++
        }

        return steps
    }

    fun solvePart2(): Int {
        val path = reader.readLine().toCharArray().toList()
        val neighbours = hashMapOf<String, List<String>>()

        // Skip empty line
        reader.readLine()

        while(true) {
            val line = reader.readLine()?: break
            val matchResult = regex.find(line)!!
            val node = matchResult.groups["node"]!!.value
            val left = matchResult.groups["left"]!!.value
            val right = matchResult.groups["right"]!!.value
            neighbours[node] = listOf(left, right)
        }

        val currentNodes = neighbours.keys.filter { node -> node.toCharArray()[2] == 'A' }.toMutableList()
        var steps = 0

        println(currentNodes)

        while (!currentNodes.all { node -> node.toCharArray()[2] == 'Z' }) {
            for ((index, node) in currentNodes.withIndex()) {
                val direction = if (path[steps % path.size] == 'L') 0 else 1
                currentNodes[index] = neighbours[node]!![direction]
            }
            steps++
            print("$currentNodes\r")
        }

        return steps
    }

}