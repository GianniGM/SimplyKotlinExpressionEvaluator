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
                "(val:\"${this.value}\", l-> ${this.left}, r-> ${this.right})"
            else "val:\"${this.value}\""
}

fun <T,C> Tree<T>.apply(transform: (T) -> C): Tree<C> = when (this) {
    is Empty -> Empty()
    is Node -> Node(transform(this.value), this.left.apply(transform), this.right.apply(transform))
    else -> throw UnsupportedOperationException("Operation Failed")
}





