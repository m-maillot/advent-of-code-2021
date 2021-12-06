fun main() {

    fun computeFish(state: Map<Int, Long>, limit: Int, day: Int = 0): Long {
        if (day == limit) {
            return state.values.sum()
        }
        val newFish = state[0] ?: 0L
        val newState = state.map { (key, value) -> Pair(key - 1, value) }
            .let { if (it.none { it.first == 6 }) it + Pair(6, 0L) else it }
            .map { (key, value) ->
                when (key) {
                    6 -> Pair(6, value + newFish)
                    else -> Pair(key, value)
                }
            }
            .filter { it.first >= 0 }
            .toMap() + mapOf(8 to newFish)

        return computeFish(newState, limit, day + 1)
    }

    fun String.toFishMap() =
        split(",")
            .map { it.toInt() }
            .groupingBy { it }
            .eachCount()
            .mapValues { (_, v) -> v.toLong() }

    fun part1(input: List<String>) =
        computeFish(input.first().toFishMap(), 80)

    fun part2(input: List<String>) =
        computeFish(input.first().toFishMap(), 256)

    val inputTest = readInputTest("Day06")
    println("---- Test ----")
    println(part1(inputTest) == 5934L)
    println(part2(inputTest) == 26984457539)

    val input = readInput("Day06")
    println("---- Part 1 ----")
    println(part1(input))
    println("---- Part 2 ----")
    println(part2(input))
}
