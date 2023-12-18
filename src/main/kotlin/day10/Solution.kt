package day10

import util.BaseSolution

class Solution(resourcePath: String) : BaseSolution(resourcePath) {

    fun solve(): Int {
        val field = reader.readLines().map { line -> line.toCharArray().toList() }
        val neighbours = listOf(Pair(-1, 0), Pair(0, -1), Pair(1, 0), Pair(0, 1))

        var start = Pair(0,0)
        for (row in field.indices) {
            for (column in field[row].indices) {
                if (field[row][column] == 'S') {
                    start = Pair(row, column)
                }
            }
        }

        val currentNodes = mutableListOf<Pair<Int, Int>>()
        val previousNodes = mutableListOf(start, start)

        for (pair in neighbours) {
            val r = start.first + pair.first
            val c = start.second + pair.second
            if (r < 0 || r >= field.size) continue
            if (c < 0 || c >= field.first().size) continue
            val neighbour = field[r][c]
            if (r == start.first) {
                if (c == start.second - 1) {
                    if (neighbour == 'F' || neighbour == 'L' || neighbour == '-') {
                        currentNodes.add(Pair(r, c))
                    }
                } else {
                    if (neighbour == 'J' || neighbour == '7' || neighbour == '-') {
                        currentNodes.add(Pair(r, c))
                    }
                }
            } else {
                if (r == start.first - 1) {
                    if (neighbour == 'F' || neighbour == '7' || neighbour == '|') {
                        currentNodes.add(Pair(r, c))
                    }
                } else {
                    if (neighbour == 'J' || neighbour == 'L' || neighbour == '|') {
                        currentNodes.add(Pair(r, c))
                    }
                }
            }
        }

        var steps = 1
        println(previousNodes)
        while (currentNodes[0] != currentNodes[1]) {
            println(currentNodes)
//            println(previousNodes)
            for (i in currentNodes.indices) {
                val (r, c) = currentNodes[i]
                val previous = previousNodes[i]
                if (field[r][c] == '.') {
                    println("Something went wrong")
                    return 0
                }
                if (field[r][c] == 'F') {
                    if (r == previous.first && c + 1 == previous.second) {
                        previousNodes[i] = currentNodes[i]
                        currentNodes[i] = Pair(r + 1, c)
                    } else {
                        previousNodes[i] = currentNodes[i]
                        currentNodes[i] = Pair(r, c + 1)
                    }
                }
                if (field[r][c] == 'L') {
                    if (r - 1 == previous.first && c == previous.second) {
                        previousNodes[i] = currentNodes[i]
                        currentNodes[i] = Pair(r, c + 1)
                    } else {
                        previousNodes[i] = currentNodes[i]
                        currentNodes[i] = Pair(r - 1, c)
                    }
                }
                if (field[r][c] == 'J') {
                    if (r - 1 == previous.first && c == previous.second) {
                        previousNodes[i] = currentNodes[i]
                        currentNodes[i] = Pair(r, c - 1)
                    } else {
                        previousNodes[i] = currentNodes[i]
                        currentNodes[i] = Pair(r - 1, c)
                    }
                }
                if (field[r][c] == '7') {
                    if (r == previous.first && c - 1 == previous.second) {
                        previousNodes[i] = currentNodes[i]
                        currentNodes[i] = Pair(r + 1, c)
                    } else {
                        previousNodes[i] = currentNodes[i]
                        currentNodes[i] = Pair(r, c - 1)
                    }
                }
                if (field[r][c] == '-') {
                    if (r == previous.first && c - 1 == previous.second) {
                        previousNodes[i] = currentNodes[i]
                        currentNodes[i] = Pair(r, c + 1)
                    } else {
                        previousNodes[i] = currentNodes[i]
                        currentNodes[i] = Pair(r, c - 1)
                    }
                }
                if (field[r][c] == '|') {
                    if (r - 1 == previous.first && c == previous.second) {
                        previousNodes[i] = currentNodes[i]
                        currentNodes[i] = Pair(r + 1, c)
                    } else {
                        previousNodes[i] = currentNodes[i]
                        currentNodes[i] = Pair(r - 1, c)
                    }
                }
            }
            steps++
        }

        return steps
    }
}