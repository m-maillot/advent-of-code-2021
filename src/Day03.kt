fun main() {

    fun part1(input: List<String>): Int {
        return input
            .fold(mapOf<Int, String>()) { acc, s ->
                s.toCharArray().mapIndexed { index, c ->
                    val values = acc.getOrDefault(index, "")
                    index to values + c
                }.toMap()
            }
            .map { (_, value) ->
                val number1 = value.count { it == '1' }
                val number0 = value.count { it == '0' }
                if (number1 > number0) 1 else 0
            }
            .let {
                val gamma = it.joinToString("").toInt(2)
                val epsilon = it.map { if (it == 1) 0 else 1 }.joinToString("").toInt(2)
                gamma * epsilon
            }
    }

    fun find(data: List<String>, most: Boolean = true, position: Int = 0): String {
        val values = data.map { it[position] }
        val total = values.size
        val number1 = values.count { it == '1' }
        val number0 = total - number1
        val valueToKeep = when {
            number1 > number0 -> if (most) '1' else '0'
            number0 > number1 -> if (most) '0' else '1'
            else -> if (most) '1' else '0'
        }
        val filteredMeasure = data.filter { it[position] == valueToKeep }
        return if (filteredMeasure.size == 1) {
            filteredMeasure.first()
        } else {
            find(filteredMeasure, most, position + 1)
        }
    }

    fun part2(input: List<String>): Int {
        val oxygen = find(input).toInt(2)
        val co2 = find(input, most = false).toInt(2)

        return oxygen * co2
    }

    val inputTest = readInputTest("Day03")
    println(part1(inputTest) == 198)
    println(part2(inputTest) == 230)
    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
