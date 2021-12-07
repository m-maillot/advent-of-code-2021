import kotlin.math.abs

fun main() {

    fun String.toIntList() = split(",").map { it.toInt() }

    fun sum(num: Int) = (num * (num + 1) / 2)

    fun List<Int>.computeEachHorizontalSum() = (0..maxOf { it })
        .map { goal -> goal to sumOf { valeur -> sum(abs(goal - valeur)) } }

    fun List<Int>.computeEachHorizontal() = (0..maxOf { it })
        .map { goal -> goal to sumOf { valeur -> abs(goal - valeur) } }

    fun part1(input: List<String>) =
        input.first()
            .toIntList()
            .computeEachHorizontal()
            .sortedBy { it.second }
            .first()
            .second

    fun part2(input: List<String>) =
        input.first()
            .toIntList()
            .computeEachHorizontalSum()
            .sortedBy { it.second }
            .first()
            .second

    val inputTest = readInputTest("Day07")
    println("---- Test ----")
    println(part1(inputTest) == 37)
    println(part2(inputTest) == 5)

    val input = readInput("Day07")
    println("---- Part 1 ----")
    println(part1(input))
    println("---- Part 2 ----")
    println(part2(input))
}
