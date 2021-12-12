import java.io.File

fun main() {
    fun parseLine(line: String): List<Pair<Int, Int>> {
        return line.split(" -> ")
                .flatMap { it. split(",") } 
                .map { it.toInt() }
                .windowed(2, 2)
                .map { Pair(it[0], it[1]) }
    }

    fun part1(list: List<String>): Int {
        val table = Array<IntArray>(1000) { _ -> IntArray(1000) { _ -> 0 } }
        var counter = 0

        list.forEach { line ->
            val p = parseLine(line)

            if (p[0].first == p[1].first) {
                val x = p[0].first
                val y1 = Math.min(p[0].second, p[1].second)
                val y2 = Math.max(p[0].second, p[1].second)
                for (y in y1..y2) {
                    table[y][x]++
                    if (table[y][x] == 2)
                        counter++
                }
            } else if (p[0].second == p[1].second) {
                val y = p[0].second
                val x1 = Math.min(p[0].first, p[1].first)
                val x2 = Math.max(p[0].first, p[1].first)
                for (x in x1..x2) {
                    table[y][x]++
                    if (table[y][x] == 2)
                        counter++
                }
            }
        }
        return counter
    }

    fun part2(list: List<String>): Int {
        val table = Array<IntArray>(1000) { _ -> IntArray(1000) { _ -> 0 } }
        var counter = 0

        list.forEach { line ->
            val p = parseLine(line)

            var (x1, y1) = p[0]
            var (x2, y2) = p[1]
            val dx = if (x1 < x2) 1 else -1
            val dy = if (y1 < y2) 1 else -1
            do {
                table[y1][x1]++
                if (table[y1][x1] == 2)
                    counter++
                if (x1 != x2) x1 += dx
                if (y1 != y2) y1 += dy
            } while (x1 != x2 || y1 != y2)
            table[y1][x1]++
            if (table[y1][x1] == 2)
                counter++
        }
        return counter
    }

    val input = File("input", "Day05.txt").readLines()
    println(part1(input))
    println(part2(input))
}