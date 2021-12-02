fun main() {

    fun List<String>.toPair() = map { it.split(" ") }
        .map { Pair(it.first(), it[1].toInt()) }

    fun part1(input: List<String>): Int {
        return input.toPair()
            .fold(Pair(0, 0)) { (position, depth), (type, value) ->
                if (type == "forward") {
                    Pair(position + value, depth)
                } else {
                    if (type == "down") {
                        Pair(position, depth + value)
                    } else {
                        Pair(position, depth - value)
                    }
                }
            }
            .let { (position, depth) -> position * depth }
    }

    fun part2(input: List<String>): Int {
        return input.toPair()
            .fold(Triple(0, 0, 0)) { (position, depth, aim), (type, value) ->
                if (type == "forward") {
                    Triple(position + value, depth + value * aim, aim)
                } else {
                    if (type == "down") {
                        Triple(position, depth, aim + value)
                    } else {
                        Triple(position, depth, aim - value)
                    }
                }
            }
            .let { (position, depth) -> position * depth }
    }

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
