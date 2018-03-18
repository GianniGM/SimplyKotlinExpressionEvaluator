// Tree = Empty | Leaf | Node of (left: Tree, right: Tree)
interface Tree<out T>

class Empty<out T> : Tree<T> {
    override fun toString(): String = "Void"
}

data class Node<out T>(
        val value: T,
        val left: Tree<T> = Empty(),
        val right: Tree<T> = Empty()
) : Tree<T> {
    override fun toString() =
            if (this.left !is Empty || this.right !is Empty)
                "(${this.value}, l-> ${this.left}, r-> ${this.right})"
            else "${this.value}"
}


fun treeEvaluator() {
    val string = Node(4,
            Node(1),
            Node(2,
                    right =     Node(3)
            )
    ).toString()
    println(string)
}




