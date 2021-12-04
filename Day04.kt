import java.io.File

fun main() {

    fun part1(list: List<String>): Int {
        var numbers = listOf<Int>()
        // For faster horizontal & vertical checking by counting the remaining items in the HashSet
        val tables = arrayListOf<Array<Array<HashSet<Int>>>>()
        // For faster lookup table so array won't be traversed O(n^2) multiple times
        val mapping = arrayListOf<HashMap<Int, Pair<Int, Int>>>()
        var counter = -1
        var row = 0
        list.forEach { line ->
            if (numbers.isEmpty()) {
                numbers = line.split(",").map { it.toInt() }
            } else {
                if (line.isBlank()) {
                    row = 0
                    counter++
                    tables.add(Array<Array<HashSet<Int>>>(2) { _ -> Array<HashSet<Int>>(5) { _ -> hashSetOf<Int>() } })
                    mapping.add(hashMapOf<Int, Pair<Int, Int>>())
                } else {
                    line.split(" ").filter { it.isNotBlank() }.forEachIndexed { column, strNum ->
                        val num = strNum.toInt()
                        tables[counter][0][row].add(num)
                        tables[counter][1][column].add(num)
                        mapping[counter][num] = Pair(row, column)
                    }
                    row++
                }
            }
        }

        numbers.forEach { num ->
            mapping.forEachIndexed { count, map -> 
                map[num]?.let { (row, column) ->
                    var rowColArray = tables[count]
                    rowColArray[0][row].remove(num)
                    if (rowColArray[0][row].isEmpty()) {
                        // Total the remaining nums
                        val total = rowColArray[0].sumOf { it.sum() }
                        return total * num
                    }

                    rowColArray[1][column].remove(num)
                    if (rowColArray[1][column].isEmpty()) {
                        // Total the remaining nums
                        val total = rowColArray[1].sumOf { it.sum() }
                        return total * num
                    }

                    map.remove(num)
                }
            }
        }

        return 0
    }

    fun part2(list: List<String>): Int {
        var numbers = listOf<Int>()
        val tables = arrayListOf<Array<Array<HashSet<Int>>>>()
        val mapping = arrayListOf<HashMap<Int, Pair<Int, Int>>>()
        var counter = -1
        var row = 0
        list.forEach { line ->
            if (numbers.isEmpty()) {
                numbers = line.split(",").map { it.toInt() }
            } else {
                if (line.isBlank()) {
                    row = 0
                    counter++
                    tables.add(Array<Array<HashSet<Int>>>(2) { _ -> Array<HashSet<Int>>(5) { _ -> hashSetOf<Int>() } })
                    mapping.add(hashMapOf<Int, Pair<Int, Int>>())
                } else {
                    line.split(" ").filter { it.isNotBlank() }.forEachIndexed { column, strNum ->
                        val num = strNum.toInt()
                        tables[counter][0][row].add(num)
                        tables[counter][1][column].add(num)
                        mapping[counter][num] = Pair(row, column)
                    }
                    row++
                }
            }
        }

        var winSet = hashSetOf<Int>()
        numbers.forEach { num ->
            mapping.forEachIndexed { count, map -> 
                map[num]?.let { (row, column) ->
                    if (winSet.contains(count))
                        return@let

                    var rowColArray = tables[count]
                    rowColArray[0][row].remove(num)
                    if (rowColArray[0][row].isEmpty()) {
                        winSet.add(count)
                        if (winSet.size == counter + 1) {
                            // Total the remaining nums
                             val total = rowColArray[0].sumOf { it.sum() }
                            return total * num
                        }
                    }

                    rowColArray[1][column].remove(num)
                    if (rowColArray[1][column].isEmpty()) {
                        winSet.add(count)
                        if (winSet.size == counter + 1) {
                            // Total the remaining nums
                             val total = rowColArray[1].sumOf { it.sum() }
                            return total * num
                        }
                    }
                    map.remove(num)
                }
            }
        }

        return 0
    }

    val input = File("input", "Day04.txt").readLines()
    println(part1(input))
    println(part2(input))
}