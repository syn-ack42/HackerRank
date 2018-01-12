package diagonal_difference

import java.util.*

fun sumDiagonal(matrix: List<List<Long>>, diagonal: Int = 1): Long {
    var ret: Long = 0
    for (i in 0..matrix.lastIndex) {
        val j = if (diagonal>0) i else matrix.lastIndex - i
        ret += matrix[i][j]
    }
    return ret
}

fun abs(d: Long): Long {
    if (d>=0) return d
    else return -d
}

fun main(args: Array<String>) {
    val sc = Scanner(System.`in`)
    val len = sc.nextInt()
    sc.nextLine()
    var matrix: MutableList<List<Long>> = mutableListOf()
    for (i in 0..(len-1)) {
        val arrStr = sc.nextLine()
        val arr = arrStr.split(" ").map { it.toLong() }
        matrix.add(arr)
    }
    val sum_sym = sumDiagonal(matrix, 1)
    val sum_asym = sumDiagonal(matrix, -1)
    println(abs(sum_asym - sum_sym))
}