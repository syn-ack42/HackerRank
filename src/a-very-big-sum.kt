import java.util.*

fun sumArrayItems(arr: List<Long>): Long {
    return arr.reduce { s, e -> s + e }
}

fun main(args: Array<String>) {
    val sc = Scanner(System.`in`)
    sc.nextLine()
    val arrStr = sc.nextLine()
    val arr = arrStr.split(" ").map { it.toLong() }
    val sum = sumArrayItems(arr)
    println(sum)
}