import java.util.*


data class Operation(private val opCode: Int, private val node: Int, private val param: Long? = null) {
    fun run() {
        when(opCode) {
            1 -> Node.nodes[node]!!.propagateValue(param!!)
            2 -> Node.nodes[node]!!.propagateConditionalValue(param!!)
            3 -> Node.nodes[node]!!.printNode()
        }
    }
}

data class Node(private val name: Int, private var value: Long = 0) {
    companion object Registry {
        val nodes = mutableMapOf<Int, Node>()
    }

    private val successors = mutableSetOf<Node>()

    init {
        nodes.put(name, this)
    }


    fun addSuccessor(node: Node) {
        successors.add(node)
    }

    fun propagateConditionalValue(v: Long) {
        if (value > v) {
            value = v
        }
        successors.forEach { it.propagateConditionalValue(v) }
    }

    fun propagateValue(v: Long) {
        value = v
        successors.forEach { it.propagateValue(v) }
    }


    fun printNode() {
        println(value)
    }
}

fun readEdges(rawEdges: List<String>) {
    rawEdges.forEach {
        it.split(" ").let {
            Node.Registry.nodes[it[0].toInt()]!!.addSuccessor(Node.Registry.nodes[it[1].toInt()]!!)
        }
    }
}

fun readOps(rawOps: List<String>): List<Operation> {
    return rawOps.map {
        it.split(" ").let { Operation(it[0].toInt(), it[1].toInt(), if (it.size == 3) it[2].toLong() else null) }
    }
}

fun run(numNodes: Int, rawEdges: List<String>, rawOps: List<String>) {
    (1..numNodes).forEach{Node(it)}
    readEdges(rawEdges)
    val ops = readOps(rawOps)
    ops.forEach { it.run() }
}

fun main(args: Array<String>) {
    val sc = Scanner(System.`in`)

    val params = sc.nextLine().split(" ")

    val numNodes = params[0].toInt()
    val numEdges = params[1].toInt()
    val numOps = params[2].toInt()

    val lines = mutableListOf<String>()
    (0..(numEdges + numOps -1)).forEach { lines.add(sc.nextLine()) }

    run(numNodes, lines.subList(0, numEdges), lines.subList(numEdges, lines.size))
}