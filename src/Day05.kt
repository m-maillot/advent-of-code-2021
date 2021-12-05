import java.lang.Integer.max
import java.lang.Integer.min

fun main() {

    data class Point(val x: Int, val y: Int)

    fun String.toPoints() =
        split(" -> ")
            .map { it.split(",").let { Point(it[0].toInt(), it[1].toInt()) } }
            .let { it[0] to it[1] }

    fun Pair<Point, Point>.explode(withDiagonal: Boolean = false) =
        if (first.x == second.x) {
            (min(first.y, second.y)..max(first.y, second.y)).map { Point(first.x, it) }
        } else if (first.y == second.y) {
            (min(first.x, second.x)..max(first.x, second.x)).map { Point(it, first.y) }
        } else {
            if (withDiagonal) {
                val startPoint = if (first.x < second.x) first else second
                val endPoint = if (first.x > second.x) first else second
                val x = endPoint.x - startPoint.x
                val y = endPoint.y - startPoint.y
                val directionY = when {
                    y > 0 -> 1
                    y < 0 -> -1
                    else -> 0
                }
                (0..x).map { delta ->
                    Point(startPoint.x + delta, startPoint.y + directionY * delta)
                }
            } else {
                emptyList()
            }
        }

    fun part1(input: List<String>): Int {
        return input.asSequence()
            .map { it.toPoints() }
            .map { it.explode() }
            .flatten()
            .groupBy { it }
            .count { (_, value) -> value.size > 1 }
    }

    fun part2(input: List<String>): Int {
        return input.asSequence()
            .map { it.toPoints() }
            .map { it.explode(withDiagonal = true) }
            .flatten()
            .groupBy { it }
            .count { (_, value) -> value.size > 1 }
    }

    val inputTest = readInputTest("Day05")
    println("---- Test ----")
    println(part1(inputTest) == 5)
    println(part2(inputTest) == 12)

    val input = readInput("Day05")
    println("---- Part 1 ----")
    println(part1(input))
    println("---- Part 2 ----")
    println(part2(input))
}
