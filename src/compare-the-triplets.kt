import java.io.*
import java.util.*

operator fun Pair<Int, Int>.plus(b: Pair<Int, Int>) = Pair(this.first + b.first, this.second + b.second)

fun compScore(a: List<Int>, b: List<Int>) :Pair<Int, Int> {
    fun scorePair(p: Pair<Int, Int>) :Pair<Int, Int> {
        if (p.first > p.second) return Pair(1, 0)
        else if (p.second > p.first) return  Pair(0,1)
        else return Pair(0,0)
    }
    val res: Pair<Int, Int> = a.zip(b).fold(Pair(0, 0)) { acc, p -> acc + scorePair(p)}
    return res
}

fun main(args: Array<String>) {
    val sc = Scanner(System.`in`)
    val aStr = sc.nextLine()
    val bStr = sc.nextLine()
    val aArr = aStr.split(" ").map { it.toInt() }
    val bArr = bStr.split(" ").map { it.toInt() }
    val res = compScore(aArr, bArr)
    println("${res.first} ${res.second}")
}