import java.util.LinkedList
import java.io.File

fun main() {

    fun part1(list: List<String>): Int {
        var table: Array<IntArray>? = null

        // Calculate the count of 0 or 1 bit in every single index
        list.forEach { binary ->
            if (table == null) {
                table = Array<IntArray>(binary.length) { _ -> intArrayOf(0,0) }
            }
            table?.let { arr ->
                binary.forEachIndexed { index, bit ->
                    val num = if (bit == '0') 0 else 1
                    arr[index][num]++
                }
            }
        }

        // Decide the gamma and epsilon bit based on the previous table counter
        val sbGamma = StringBuilder()
        val sbEpsilon = StringBuilder()
        table?.forEach { intArr ->
            if (intArr[0] > intArr[1]) {
                sbGamma.append("0")
                sbEpsilon.append("1")
            } else {
                sbGamma.append("1")
                sbEpsilon.append("0")
            }
        }

        // Convert binary string into integer value
        val gamma = sbGamma.toString().toInt(2)
        val epsilon = sbEpsilon.toString().toInt(2)
        //println("${sbGamma.toString()} -> $gamma | ${sbEpsilon.toString()} -> $epsilon | ${gamma * epsilon}")
        return gamma * epsilon
    }

    fun part2(list: List<String>): Int {
        // LinkedList is a doubly linked list under the hood so the removal process from iterator will be O(1)
        var listG = LinkedList<String>()
        var listE = LinkedList<String>()

        list.forEach {
            listG.add(it)
            listE.add(it)
        }

        val sbGamma = StringBuilder()
        val sbEpsilon = StringBuilder()

        var prevCharG = ' '
        var prevCharE = ' '

        val length = listG.first().length
        for (index in 0..length - 1) {
            // Count 0 or 1 bit for every single index
            val intArrG = intArrayOf(0, 0)
            var iteratorG = listG.listIterator()
            while (iteratorG.hasNext()) {
                val binary = iteratorG.next()
                val char = binary[index]
                // Remove and ignore the current binary if previous index char is not the previous char with max count
                if (index > 0 && binary[index - 1] != prevCharG) {
                    iteratorG.remove()
                    continue
                }
                val num = if (char == '0') 0 else 1
                intArrG[num]++
            }
            // Set the last binary as the binary value if there is only 1 binary string remaining
            if (listG.size == 1) {
                sbGamma.clear()
                sbGamma.append(listG.first())
            } else if (listG.size > 1) {
                prevCharG = if (intArrG[1] >= intArrG[0]) '1' else '0'
                sbGamma.append(prevCharG)
            }

            // Count 0 or 1 bit for every single index
            val intArrE = intArrayOf(0, 0)
            var iteratorE = listE.listIterator()
            while (iteratorE.hasNext()) {
                val binary = iteratorE.next()
                val char = binary[index]
                // Remove and ignore the current binary if previous index char is not the previous char with min count
                if (index > 0 && binary[index - 1] != prevCharE) {
                    iteratorE.remove()
                    continue
                }
                val num = if (char == '0') 0 else 1
                intArrE[num]++
            }
            // Set the last binary as the binary value if there is only 1 binary string remaining
            if (listE.size == 1) {
                sbEpsilon.clear()
                sbEpsilon.append(listE.first())
            } else if (listE.size > 1) {
                prevCharE = if (intArrE[0] <= intArrE[1]) '0' else '1'
                sbEpsilon.append(prevCharE)
            }
        }

        // Convert binary string into integer value
        val gamma = sbGamma.toString().toInt(2)
        val epsilon = sbEpsilon.toString().toInt(2)
        // println("${sbGamma.toString()} -> $gamma | ${sbEpsilon.toString()} -> $epsilon | ${gamma * epsilon}")
        return gamma * epsilon
    }

    val input = File("input", "Day03.txt").readLines()
    println(part1(input))
    println(part2(input))
}