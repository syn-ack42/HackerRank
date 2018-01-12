import java.util.*

fun sumArrayItems(arr: List<Int>): Int {
    return arr.reduce { s, e -> s + e }
}

fun main(args: Array<String>) {
    val sc = Scanner(System.`in`)
    sc.nextLine()
    val arrStr = sc.nextLine()
    val arr = arrStr.split(" ").map { it.toInt() }
    val sum = sumArrayItems(arr)
    println(sum)
}

