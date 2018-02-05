package magic_square_forming

import java.util.*

val origMagicSquare: List<List<Int>> = listOf(
                                            listOf(2, 7, 6),
                                            listOf(9, 5, 1),
                                            listOf(4, 3, 8)
                                            )

fun rotateSquare(square: List<List<Int>>, by: Int): List<List<Int>> {
    return when(by) {
        0 -> square
        1 -> listOf(listOf(square[2][0], square[1][0], square[0][0]),
                    listOf(square[2][1], square[1][1], square[0][1]),
                    listOf(square[2][2], square[1][2], square[0][2]))
        2 -> rotateSquare(rotateSquare(square, 1), 1)
        3 -> rotateSquare(rotateSquare(square, 2), 1)
        else -> throw (RuntimeException("screw up!"))
    }
}

fun flipSquare(square: List<List<Int>>): List<List<Int>> {
    return listOf(  listOf(square[2][0], square[2][1], square[2][2]),
                    listOf(square[1][0], square[1][1], square[1][2]),
                    listOf(square[0][0], square[0][1], square[0][2]))
}

fun printSquare(square: List<List<Int>>) {
    square.forEach { println(it.joinToString(separator = " ", transform = {it.toString()})) }
}

fun abs(d: Int): Int {
    return if (d>=0) d
    else -d
}

fun squaresDistance(square1: List<List<Int>>, square2: List<List<Int>>): Int {
    var sum = 0

    for (y in 0..2) {
        for (x in 0..2) {
            sum += abs(square1[y][x] - square2[y][x])
        }
    }

    return sum
}

val allSquares = listOf(
        origMagicSquare,
        rotateSquare(origMagicSquare,1),
        rotateSquare(origMagicSquare,2),
        rotateSquare(origMagicSquare,3),
        flipSquare(origMagicSquare),
        rotateSquare(flipSquare(origMagicSquare),1),
        rotateSquare(flipSquare(origMagicSquare),2),
        rotateSquare(flipSquare(origMagicSquare),3)
)


fun main(args: Array<String>) {
    val sc = Scanner(System.`in`)

    val row0 = sc.nextLine().split(" ").map { it.toInt()}
    val row1 = sc.nextLine().split(" ").map { it.toInt()}
    val row2 = sc.nextLine().split(" ").map { it.toInt()}

    val matrix: List<List<Int>> = listOf(row0, row1, row2)
    var minDist = 99999

//    println("in matrix:")
//    printSquare(matrix)

//    println("\nall magic squares:")
    allSquares.forEach {
//        printSquare(it)
        val d = squaresDistance(matrix, it)
//        println("distance: $d")
        if (d<minDist) {
            minDist = d
        }
    }

    print(minDist)

}