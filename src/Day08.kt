fun main() {


    fun part1(input: List<String>) =
        input.map { it.substringAfter(" | ") }
            .flatMap { it.split(" ") }
            .count { it.length in listOf(2, 3, 4, 7) }

    fun buildPattern(input: List<Set<Char>>): Array<Set<Char>> {
        val words = (0..8).map { index -> input.filter { it.size == index } }
        val arr = Array(10) { setOf<Char>() }
        arr[1] = words[2].first()
        arr[4] = words[4].first()
        arr[7] = words[3].first()
        arr[8] = words[7].first()
        arr[6] = words[6].first { it + arr[1] == arr[8] }
        arr[5] = words[5].first { it + arr[6] != arr[8] }
        arr[2] = words[5].first { it + arr[5] == arr[8] }
        arr[9] = words[6].first { it + arr[4] != arr[8] }
        arr[0] = words[6].first { it + arr[5] == arr[8] }
        arr[3] = words[5].first { it !in arr }
        return arr
    }

    fun String.toListSet() = split(" ").map { it.toSet() }

    fun part2(input: List<String>) =
        input.asSequence()
            .map { line -> line.substringBefore(" | ").toListSet() to line.substringAfter(" | ").toListSet() }
            .map { (left, right) -> buildPattern(left) to right }
            .map { (pattern, right) -> right.map { pattern.indexOf(it) } }
            .map { it.joinToString("").toInt() }
            .sum()

    val inputTest = readInputTest("Day08")
    println("---- Test ----")
    println(part1(inputTest) == 26)
    println(part2(inputTest) == 61229)

    val input = readInput("Day08")
    println("---- Part 1 ----")
    println(part1(input))
    println("---- Part 2 ----")
    println(part2(input))
}
