fun main() {

    fun List<Int>.increaseCount() = foldIndexed(0) { index, acc, current ->
        val previousValue = getOrNull(index - 1)
        if (previousValue != null && previousValue < current) {
            acc + 1
        } else {
            acc
        }
    }

    fun List<Int>.sumByThree() = mapIndexedNotNull { index, value ->
        if (getOrNull(index + 2) != null) {
            value + this[index + 1] + this[index + 2]
        } else {
            null
        }
    }

    fun part1(input: List<String>): Int {
        return input.map { it.toInt() }.increaseCount()
    }

    fun part2(input: List<String>): Int {
        return input.map { it.toInt() }
            .sumByThree()
            .increaseCount()
    }

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
