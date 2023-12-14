package day3

class Solution {
    private val inputStream = this::class.java.getResourceAsStream("/day3/input1")!!
    private val neighbours = listOf(Pair(1, 0), Pair(-1, 0), Pair(1, 1), Pair(0, 1), Pair(-1, 1), Pair(1, -1), Pair(0, -1), Pair(-1, -1))
    fun solve(): Int {
        val field = inputStream.bufferedReader().readLines().map { line -> line.toMutableList() }

        var total = 0;

        for ((j, line) in field.withIndex()) {
            for ((i, char) in line.withIndex()) {
                if (char.isDigit()) {
                    for ((x, y) in neighbours) {
                        val nX = i + x;
                        val nY = j + y;
                        if (nX < 0 || nX >= line.size) {
                            continue
                        }
                        if (nY < 0 || nY >= field.size) {
                            continue
                        }
                        val nC = field[nY][nX]
                        if (!nC.isDigit() && nC != '.') {
                            val number = findNumber(line, i).toInt();
                            println("Adding $number")
                            total += number
                            break
                        }
                    }
                }
            }
        }
        return total
    }

    fun solve2(): Int {
        val field = inputStream.bufferedReader().readLines().map { line -> line.toMutableList() }

        val above = listOf(Pair(0, 1), Pair(1, 1), Pair(-1, 1))
        val side = listOf(Pair(1, 0), Pair(-1, 0))
        val below = listOf(Pair(1, -1), Pair(0, -1), Pair(-1, -1))

        var total = 0;

        for ((j, line) in field.withIndex()) {
            for ((i, char) in line.withIndex()) {
                if (char == '*') {
                    val found = mutableListOf<Pair<Int, Int>>()
                    for ((x, y) in neighbours) {
                        val nX = i + x;
                        val nY = j + y;
                        if (nX < 0 || nX >= line.size) {
                            continue
                        }
                        if (nY < 0 || nY >= field.size) {
                            continue
                        }
                        val nC = field[nY][nX]
                        if (nC.isDigit()) {
                            found.add(Pair(nX, nY))
                        }
                    }
                    println("Found $found")
                    var gearRatio = 1;
                    var n = 0;
                    for ((x, y) in found) {
                        val result = findNumber(field[y], x)
                        if (result != "") {
                            n++;
                            gearRatio *= result.toInt()
                        }
                    }
                    if (n == 2) {
                        println("Adding gear ratio $gearRatio")
                        total += gearRatio;
                    }


                }
            }
        }
        return total;
    }

    private fun findNumber(line: MutableList<Char>, pos: Int): String {
        if (pos >= line.size || pos < 0) return ""
        val c = line[pos];
        return if (!c.isDigit()) {
            "";
        } else {
            line[pos] = '.';
            findNumber(line, pos-1) + c + findNumber(line, pos+1)
        }
    }

}