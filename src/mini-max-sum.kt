package mini_max_sum

import java.util.*

fun main(args: Array<String>) {
    val sc = Scanner(System.`in`)
    val arrStr = sc.nextLine()
    val arr: List<Long> = arrStr.split(" ")
            .map { it.toLong() }
            .sorted()
    val lsum: Long = (0..(arr.lastIndex -1)).fold(0) { acc: Long, i: Int -> acc + arr[i] }
    val hsum: Long = (1..(arr.lastIndex)).fold(0) { acc: Long, i: Int -> acc + arr[i] }
    print("$lsum $hsum")
}