package staircase

import java.util.*

fun main(args: Array<String>) {
    print(Scanner(System.`in`).nextLong().let { it -1 }.let { (0..it).map { line -> (0..it).fold("", { acc, loc -> "$acc${if (loc + line >= it) '#' else ' '}" }) }.joinToString("\n") })
}