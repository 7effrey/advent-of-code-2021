import java.io.File
import java.util.LinkedList

fun main() {

    fun part1(list: List<String>): Int {
        var prev = -1
        var counter = 0
        list.forEach {
            val current = it.toInt()
            if (prev != -1 && current > prev) {
                counter++ 
            }
            prev = current
        }
        return counter
    }

    fun part2(list: List<String>): Int {
        val window = LinkedList<Int>()
        var windowTotal = 0
        var prev = -1
        var counter = 0
        list.forEach {
            val current = it.toInt()
            window.add(current)
            windowTotal += current
            // Ensure that sliding window will have maximum 3 items
            if (window.size > 3) {
                windowTotal -= window.removeFirst()
            }
            if (window.size == 3) {
                if (prev != -1 && windowTotal > prev) {
                    counter++ 
                }
                prev = windowTotal
            }
        }
        return counter
    }

    val input = File("input", "Day01.txt").readLines()
    println(part1(input))
    println(part2(input))
}