import java.io.File

fun main() {

    fun part1(list: List<String>): Int {
        var horizontal = 0
        var depth = 0
        list.forEach {
            val tokens = it.split(" ")
            val command = tokens[0]
            val value = tokens[1].toInt()

            when (command) {
                "forward" -> horizontal += value
                "down" -> depth += value
                "up" -> depth -= value
            }
        }
        return horizontal * depth
    }

    fun part2(list: List<String>): Int {
        var horizontal = 0
        var depth = 0
        var aim = 0
        list.forEach {
            val tokens = it.split(" ")
            val command = tokens[0]
            val value = tokens[1].toInt()

            when (command) {
                "forward" -> {
                    horizontal += value
                    depth += (aim * value)
                }
                "down" -> aim += value
                "up" -> aim -= value
            }
        }
        return horizontal * depth
    }

    val input = File("input", "Day02.txt").readLines()
    println(part1(input))
    println(part2(input))
}