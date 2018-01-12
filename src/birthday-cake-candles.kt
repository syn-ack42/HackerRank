package birthday_cake_candles

import java.util.*

fun main(args: Array<String>) {
    val sc = Scanner(System.`in`)
    sc.nextLine()
    val arrStr = sc.nextLine()
    val arr: List<Long> = arrStr.split(" ")
            .map { it.toLong() }
            .sorted().reversed()
    val largest: Long = arr[0]
    var count: Long = 0
    arr.forEach {s: Long -> if (s == largest) count++ else return@forEach}
    print("$count")
}