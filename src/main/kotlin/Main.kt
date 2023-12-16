import day1.Solution as Day1Solution
import day2.Solution as Day2Solution
import day3.Solution as Day3Solution
import day4.Solution as Day4Solution
import day5.Solution as Day5Solution
import day6.Solution as Day6Solution

import day8.Solution as Day8

fun main() {
    val day1Solution = Day1Solution();
    val day2Solution = Day2Solution();
    val day3Solution = Day3Solution();
    val day4Solution = Day4Solution();
    val day5Solution = Day5Solution();
    val day6Solution = Day6Solution();

    val day8 = Day8("/day8/input1");

    println(day8.solvePart2())
}