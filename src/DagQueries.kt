import java.util.*


data class Operation(private val opCode: Int, private val node: Int, private val param: Long? = null) {
    fun run() {
        val target = Node.nodes[node]
        when(opCode) {
            1 -> target?.propagateSetValue(param?: throw RuntimeException("Invalid Op $opCode, $node, $param"))
            2 -> target?.propagateSetValue(param?: throw RuntimeException("Invalid Op $opCode, $node, $param"), param)
            3 -> if (target != null) target.printNode() else println(0)
            else -> throw RuntimeException("Invalid Op $opCode, $node, $param")
        }
    }
}

data class Node(private val name: Int) {
    companion object Registry {
        val nodes = mutableMapOf<Int, Node>()
        val rootNodes = mutableSetOf<Node>()
    }

    private var value: Long = 0
    private val successors = mutableSetOf<Node>()
    private val downstreamNodes = mutableSetOf<Node>()

    init {
        nodes.put(name, this)
        rootNodes.add(this)
    }


    fun addSuccessor(node: Node) {
        successors.add(node)
    }

    fun discoverDownstream(): Set<Node> {
        for (n in successors) {
            downstreamNodes.addAll(n.discoverDownstream())
        }
        downstreamNodes.addAll(successors)
        return downstreamNodes.toSet()
    }

    fun propagateSetValue(v: Long, cond: Long = -1) {
        if (value > cond) {
            value = v
        }
        downstreamNodes.forEach { it.setValue(v, cond) }
    }

    private fun setValue(v: Long, cond: Long = -1) {
        if (value > cond) {
            value = v
        }
    }

    fun printNode() {
        println(value)
    }
}

fun readEdges(rawEdges: List<String>) {
    rawEdges.forEach {
        val e = it.split(" ").map { it.toInt() }
        val f = if (e[0] in Node.Registry.nodes) Node.Registry.nodes[e[0]] else Node(e[0])
        val t = if (e[1] in Node.Registry.nodes) Node.Registry.nodes[e[1]] else Node(e[1])
        f?.addSuccessor(t?: throw RuntimeException("Impossible"))
        Node.rootNodes.remove(t)
    }
}

fun readOps(rawOps: List<String>): List<Operation> {
    return rawOps.map {
        it.split(" ").let { Operation(it[0].toInt(), it[1].toInt(), if (it.size > 2) it[2].toLongOrNull() else null) }
    }
}

fun run(numNodes: Int, rawEdges: List<String>, rawOps: List<String>) {
    (1..numNodes).forEach{Node(it)}
    readEdges(rawEdges)
    Node.rootNodes.forEach { it.discoverDownstream() }
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
    var i = 0
    while (i < (numEdges + numOps)) {
        i ++
        lines.add(sc.nextLine())
    }

    run(numNodes, lines.subList(0, numEdges), lines.subList(numEdges, lines.size))
}