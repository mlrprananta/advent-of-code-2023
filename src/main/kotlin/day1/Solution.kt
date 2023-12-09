package day1


class Solution {
    private val inputStream = this::class.java.getResourceAsStream("/day1/input1.txt")

    fun solve(): Int {
        var sum = 0;
        for (line in inputStream?.bufferedReader()?.readLines()!!) {
            val value = extractValue(line)
            sum += value;
        }
        return sum;
    }

    private fun extractValue(line: String): Int {
        println("New line:")
        var value = 0;
        val charArray = line.toCharArray()
        for (i in 0..line.length) {
            val char = charArray[i]
            if (char.isDigit()) {
                println("First digit found: $char")
                value += 10 * char.digitToInt();
                break
            } else {
                val potentialValue = extractActualValue(i, charArray);
                if (potentialValue != 0) {
                    println("First hidden digit found: $potentialValue")
                    value += potentialValue * 10;
                    break;
                }
            }
        }
        for (i in line.length-1 downTo 0) {
            val char = charArray[i]
            if (char.isDigit()) {
                println("Last digit found: $char")
                value += char.digitToInt();
                break
            } else {
                val potentialValue = extractActualValue(i, charArray);
                if (potentialValue != 0) {
                    println("Last hidden digit found: $potentialValue")
                    value += potentialValue;
                    break;
                }
            }
        }
        println("Value is $value")
        return value
    }

    private fun extractActualValue(i: Int, chars: CharArray): Int {
        // string length: 3, 4, 5
        val stringToInt = mapOf<String, Int>(
            "one" to 1,
            "two" to 2,
            "three" to 3,
            "four" to 4,
            "five" to 5,
            "six" to 6,
            "seven" to 7,
            "eight" to 8,
            "nine" to 9,
        )
        if (i+3 > chars.size) return 0;
        val word1 = buildString {
            for (j in i..i+2) {
                append(chars[j]);
            }
        }
        if (stringToInt.containsKey(word1)) {
            return stringToInt.getValue(word1);
        }

        if (i+4 > chars.size) return 0;
        val word2 = buildString {
            for (j in i..i+3) {
                append(chars[j]);
            }
        }
        if (stringToInt.containsKey(word2)) {
            return stringToInt.getValue(word2);
        }

        if (i+5 > chars.size) return 0;
        val word3 = buildString {
            for (j in i..i+4) {
                append(chars[j]);
            }
        }
        if (stringToInt.containsKey(word3)) {
            return stringToInt.getValue(word3);
        }

        return 0
    }


}