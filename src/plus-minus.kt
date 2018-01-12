package plus_minus

import java.util.*

fun Double.format(digits: Int) = java.lang.String.format("%.${digits}f", this)

data class Percentages(val p_pos: Double, val p_neg: Double, val p_zero: Double) {
    override fun toString(): String {
        return "%.6f\n%.6f\n%.6f".format(p_pos, p_neg, p_zero)
    }
}

fun calcPercent(list: List<Long>): Percentages {
    var c_pos = 0.0
    var c_neg = 0.0
    var c_zer = 0.0
    for (i in list) {
        when (true) {
            i>0 -> c_pos++
            i<0 -> c_neg++
            else -> c_zer++
        }
    }
    val l = list.size.toDouble()
    return Percentages(p_pos = (c_pos/l), p_neg = c_neg/l, p_zero = c_zer/l)
}


fun main(args: Array<String>) {
    val sc = Scanner(System.`in`)
    sc.nextLine()
    val arrStr = sc.nextLine()
    val arr = arrStr.split(" ").map { it.toLong() }
    val perc = calcPercent(arr)
    println(perc)
}