fun main(args: Array<String>) {
    expressionEvaluator()

}

fun expressionEvaluator() {
//     5 + (2^3)
    val sumAndElevation = Sum(Num(5), Elevation(Num(2), Num(3)))
    println("evaluated ${eval(sumAndElevation)}")

//    if (5 + 2 == 8)
//        2^3
//    else
//        20 - 11
    val ifThenElse: Expr = IfThenElse(
            Equal(
                    Sum(
                            Num(5),
                            Num(2)
                    ),
                    Num(8)
            ),
            Elevation(
                    Num(2),
                    Num(3)
            ),
            Minus(
                    Num(20),
                    Num(11)
            )
    )
    println("evaluated ${eval(ifThenElse)}")

    val normalBTree = Node(4,
            Node(1),
            Node(2,
                    right = Node(3)
            )
    ).toString()
    println(normalBTree)

    val sExpr = Node(
            Sum(
                    Num(3),
                    Num(4)
            ),
            Node(
                    Elevation(
                            Num(2),
                            Num(3)
                    ),
                   right = Node(ifThenElse)
            )
    )
    println(sExpr)

    val newTree = sExpr.apply {
        eval(it)
    }
    println(newTree)
}
