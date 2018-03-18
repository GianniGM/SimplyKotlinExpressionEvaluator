interface Expr

class Num(val value: Int) : Expr
class Bool(val value: Boolean) : Expr

class Not(val value: Expr) : Expr
class Equal(val first: Expr, val second: Expr) : Expr
class Minor(val first: Expr, val second: Expr) : Expr
class Major(val first: Expr, val second: Expr) : Expr

class Elevation(val base: Expr, val exp: Expr) : Expr
class Sum(val first: Expr, val second: Expr) : Expr
class Minus(val first: Expr, val second: Expr) : Expr
class Multiply(val first: Expr, val second: Expr) : Expr
class IfThenElse(val statement: Expr, val then: Expr, val elze: Expr) : Expr

fun expressionEvaluator() {

//     5 + (2^3)
    val sumAndElevation = Sum(Num(5), Elevation(Num(2), Num(3)))

//    if (5 + 2 == 8)
//        2^3
//    else
//        20 - 11

    val b = IfThenElse(
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


    println("evaluated ${eval(b)}")
}

infix fun Int.elevate(exp: Int): Int {
    var r = this
    for (i in 1 until exp)
        r *= this
    return r
}

fun eval(e: Expr): Int = when (e) {
    is Num -> e.value
    is Sum -> eval(e.first) + eval(e.second)
    is Multiply -> eval(e.first) * eval(e.second)
    is Minus -> eval(e.first) - eval(e.second)
    is Elevation -> eval(e.base) elevate eval(e.exp)
    is IfThenElse -> when (bval(e.statement)) {
        true -> eval(e.then)
        false -> eval(e.elze)
    }
    else -> throw IllegalArgumentException("Unknown Expression")
}

fun bval(b: Expr): Boolean = when (b) {
    is Bool -> b.value
    is Not -> !bval(b)
    is Equal -> eval(b.first) == eval(b.second)
    is Minor -> eval(b.first) < eval(b.second)
    is Major -> eval(b.first) > eval(b.second)
    else -> throw  IllegalArgumentException("Unknkown Bool Expression")
}