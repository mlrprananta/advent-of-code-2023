package day5

import java.io.BufferedReader

class Solution {
    private val seedToSoilMap = mutableMapOf<ULongRange, ULong>()
    private val soilToFertilizerMap = mutableMapOf<ULongRange, ULong>()
    private val fertilizerToWaterMap = mutableMapOf<ULongRange, ULong>()
    private val waterToLightMap = mutableMapOf<ULongRange, ULong>()
    private val lightToTemperatureMap = mutableMapOf<ULongRange, ULong>()
    private val temperatureToHumidityMap = mutableMapOf<ULongRange, ULong>()
    private val humidityToLocationMap = mutableMapOf<ULongRange, ULong>()

    private val numberRegex = """(\d+)""".toRegex()
    private val mapRegex = """(?<destination>\d+) (?<source>\d+) (?<range>\d+)""".toRegex()

    fun solve(resourcePath: String): ULong {
        val inputStream = this::class.java.getResourceAsStream(resourcePath)!!
        val reader = inputStream.bufferedReader()

        val seeds = extractSeeds(reader.readLine())
        reader.readLine()
        extractMap(reader, seedToSoilMap)
        extractMap(reader, soilToFertilizerMap)
        extractMap(reader, fertilizerToWaterMap)
        extractMap(reader, waterToLightMap)
        extractMap(reader, lightToTemperatureMap)
        extractMap(reader, temperatureToHumidityMap)
        extractMap(reader, humidityToLocationMap)

        val locations = seeds.asSequence().map { seed ->
            checkMap(seedToSoilMap, seed)
        }.map { soil ->
            checkMap(soilToFertilizerMap, soil)
        }.map { fertilizer ->
            checkMap(fertilizerToWaterMap, fertilizer)
        }.map { water ->
            checkMap(waterToLightMap, water)
        }.map { light ->
            checkMap(lightToTemperatureMap, light)
        }.map { temperature ->
            checkMap(temperatureToHumidityMap, temperature)
        }.map { humidity ->
            checkMap(humidityToLocationMap, humidity)
        }.sortedBy { x -> x }.toList()

        println(locations)

        return locations[0]
    }

    fun solve2(resourcePath: String): ULong {
        val inputStream = this::class.java.getResourceAsStream(resourcePath)!!
        val reader = inputStream.bufferedReader()

        val seeds = extractRangeOfSeeds(reader.readLine())
        println("Seeds: $seeds")
        reader.readLine()
        extractMap(reader, seedToSoilMap)
        extractMap(reader, soilToFertilizerMap)
        extractMap(reader, fertilizerToWaterMap)
        extractMap(reader, waterToLightMap)
        extractMap(reader, lightToTemperatureMap)
        extractMap(reader, temperatureToHumidityMap)
        extractMap(reader, humidityToLocationMap)

        var lowest = ULong.MAX_VALUE
        for (rangeSeeds in seeds) {
            println("Processing $rangeSeeds")
            rangeSeeds.asSequence().map { seed ->
                checkMap(seedToSoilMap, seed)
            }.map { soil ->
                checkMap(soilToFertilizerMap, soil)
            }.map { fertilizer ->
                checkMap(fertilizerToWaterMap, fertilizer)
            }.map { water ->
                checkMap(waterToLightMap, water)
            }.map { light ->
                checkMap(lightToTemperatureMap, light)
            }.map { temperature ->
                checkMap(temperatureToHumidityMap, temperature)
            }.map { humidity ->
                checkMap(humidityToLocationMap, humidity)
            }.forEach { location -> lowest = setLowest(location, lowest) }
        }


        return lowest
    }

    private fun setLowest(location: ULong, lowest: ULong): ULong {
        return if (location < lowest) {
            println("New lowest: $location")
            location
        } else {
            lowest
        }
    }

    private fun extractSeeds(line: String?): List<ULong> {
        assert(line != null)
        return numberRegex.findAll(line!!)
            .map { matchResult -> matchResult.value.toULong() }
            .toList()
    }

    private fun extractRangeOfSeeds(line: String?): List<ULongRange> {
        assert(line != null)
        val seeds = mutableListOf<ULongRange>()
        val results = numberRegex.findAll(line!!).toList()
        var last: ULong? = null
        for ((index, result) in results.withIndex()) {
            val value = result.value.toULong()
            if ((index + 1) % 2 == 0) {
                seeds.add(ULongRange(last!!, last+value))
            } else {
                last = value
            }
        }
        return seeds
    }

    private fun extractMap(reader: BufferedReader, map: MutableMap<ULongRange, ULong>) {
        val description = reader.readLine()
        println("Processing $description")
        while (true) {
            val line = reader.readLine()
            if (line == "" || line == null) break
            val result = mapRegex.find(line)!!
            val destination = result.groups["destination"]!!.value.toULong()
            val source = result.groups["source"]!!.value.toULong()
            val range = result.groups["range"]!!.value.toULong()
            map[ULongRange(source, source + range - 1u)] = destination
        }
    }

    private fun checkMap(map: Map<ULongRange, ULong>, value: ULong): ULong {
        for ((range, destination) in map) {
            if (range.contains(value)) {
                return destination + (value - range.first)
            }
        }
        return value
    }

}