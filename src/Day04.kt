fun main() {

    data class Board(
        val id: Int,
        val line1: List<Int>,
        val line2: List<Int>,
        val line3: List<Int>,
        val line4: List<Int>,
        val line5: List<Int>,
        val lineUnpicked: List<List<Int>> = listOf(line1, line2, line3, line4, line5),
        val columnUnpicked: List<List<Int>> = line1.mapIndexed { index, i ->
            listOf(
                i,
                line2[index],
                line3[index],
                line4[index],
                line5[index]
            )
        },
    ) {
        fun hasCompleted() = lineUnpicked.any { it.isEmpty() } || columnUnpicked.any { it.isEmpty() }

        fun sumUnpicked(): Int = lineUnpicked.flatten().sum()
    }

    fun String.convertToLineBoard() = split(" ").filter { it.isNotBlank() }.map { it.trim().toInt() }

    fun List<String>.toBoard(index: Int) = Board(
        index,
        this[0].convertToLineBoard(),
        this[1].convertToLineBoard(),
        this[2].convertToLineBoard(),
        this[3].convertToLineBoard(),
        this[4].convertToLineBoard(),
    )

    fun splitBoards(
        input: List<String>,
        boards: List<List<String>> = emptyList(),
        currentBoard: List<String> = emptyList(),
        currentIndex: Int = 0
    ): List<List<String>> {
        if (currentIndex == input.size) {
            return boards + listOf(currentBoard)
        }
        val currentLine = input[currentIndex]
        if (currentLine.isBlank()) {
            return splitBoards(input, boards + listOf(currentBoard), emptyList(), currentIndex + 1)
        }
        return splitBoards(input, boards, currentBoard + currentLine, currentIndex + 1)
    }

    fun play(
        draws: List<Int>,
        boards: List<Board>,
        winners: List<Pair<Int, Board>> = emptyList(),
        currentIndex: Int = 0
    ): List<Pair<Int, Board>> {
        if (currentIndex == draws.size || boards.isEmpty()) {
            return winners
        }
        val draw = draws[currentIndex]
        val playedBoards = boards.map { board ->
            board.copy(
                lineUnpicked = board.lineUnpicked.map { it - draw },
                columnUnpicked = board.columnUnpicked.map { it - draw },
            )
        }
        val winningBoards = playedBoards.filter { it.hasCompleted() }
        if (winningBoards.isNotEmpty()) {
            return play(
                draws,
                playedBoards - winningBoards,
                winners + winningBoards.map { draw to it },
                currentIndex + 1
            )
        }
        return play(draws, playedBoards, winners, currentIndex + 1)

    }

    fun part1(input: List<Pair<Int, Board>>): Int {
        return input.first().let { (draw, board) -> draw * board.sumUnpicked() }
    }

    fun part2(input: List<Pair<Int, Board>>): Int {
        return input.last().let { (draw, board) -> draw * board.sumUnpicked() }
    }

    val inputTest = readInputTest("Day04")
    val drawsTest = inputTest.first().split(",").map { it.toInt() }
    val boardsTest = splitBoards(inputTest.drop(2)).mapIndexed { index, list -> list.toBoard(index) }
    val resultTest = play(drawsTest, boardsTest)
    println("---- Test ----")
    println(part1(resultTest) == 4512)
    println(part2(resultTest) == 1924)

    val input = readInput("Day04")
    val draws = input.first().split(",").map { it.toInt() }
    val boards = splitBoards(input.drop(2)).mapIndexed { index, list -> list.toBoard(index) }
    val result = play(draws, boards)
    println("---- Part 1 ----")
    println(part1(result))
    println("---- Part 2 ----")
    println(part2(result))
}