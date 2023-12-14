package day4

class Solution {
    fun solve(resourcePath: String): Int {
        val inputStream = this::class.java.getResourceAsStream(resourcePath)!!
        val cards = inputStream.bufferedReader().readLines()

        val cardIdRegex = """Card *(?<cardId>\d+)""".toRegex()
        val winningNumbersSectionRegex = """:.+\|""".toRegex()
        val numbersSectionRegex = """\|.+""".toRegex()
        val numberRegex = """ *(?<number>\d+) *""".toRegex()
        var total = 0;
        for (card in cards) {
//            val cardId = cardIdRegex.find(card)!!.groups["cardId"]!!.value.toInt()
            val winningNumbers = mutableSetOf<Int>()
            var points = 0;
            for (result in numberRegex.findAll(winningNumbersSectionRegex.find(card)!!.value)) {
                winningNumbers.add(result.groups["number"]!!.value.toInt())
            }
            for (result in numberRegex.findAll(numbersSectionRegex.find(card)!!.value)) {
                val number = result.groups["number"]!!.value.toInt()
                if (winningNumbers.contains(number)) {
                    if (points == 0)
                        points = 1
                    else
                        points *= 2
                }
            }
            total += points
        }

        return total

    }

    fun solve2(resourcePath: String): Int {
        val cardDeck = mutableMapOf<Int, Int>()
        val cardRewards = mutableMapOf<Int, Int>()
        val inputStream = this::class.java.getResourceAsStream(resourcePath)!!
        val cards = inputStream.bufferedReader().readLines()

        val cardIdRegex = """Card *(?<cardId>\d+)""".toRegex()
        val winningNumbersSectionRegex = """:.+\|""".toRegex()
        val numbersSectionRegex = """\|.+""".toRegex()
        val numberRegex = """ *(?<number>\d+) *""".toRegex()
        var total = 0;

        for (card in cards) {
            val cardId = cardIdRegex.find(card)!!.groups["cardId"]!!.value.toInt()
            cardDeck[cardId] = 1
        }


        for (cardId in cardDeck.keys) {
            val card = cards[cardId-1]
            val winningNumbers = mutableSetOf<Int>()
            var matches = 0;
            for (result in numberRegex.findAll(winningNumbersSectionRegex.find(card)!!.value)) {
                winningNumbers.add(result.groups["number"]!!.value.toInt())
            }
            for (result in numberRegex.findAll(numbersSectionRegex.find(card)!!.value)) {
                val number = result.groups["number"]!!.value.toInt()
                if (winningNumbers.contains(number)) {
                    matches++
                }
            }
            cardRewards[cardId] = matches
        }

        for ((cardId, matches) in cardRewards) {
            for (i in 1..matches) {
                println(matches)
                if (cardId + i >= cardDeck.size + 1) break
                cardDeck[cardId+i] = cardDeck[cardId+i]!! + cardDeck[cardId]!!
            }
        }

        return cardDeck.values.sum()

    }

}