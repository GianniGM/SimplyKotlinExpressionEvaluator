interface Expr

class Num(val value: Int) : Expr {
    override fun toString() = "$value"
}

class Bool(val value: Boolean) : Expr {
    override fun toString() = "$value"
}

class Not(val value: Expr) : Expr {
    override fun toString() = "~$value"
}

class Equal(val first: Expr, val second: Expr) : Expr {
    override fun toString() = "$first == $second"
}

class Minor(val first: Expr, val second: Expr) : Expr {
    override fun toString() = "$first < $second"
}

class Major(val first: Expr, val second: Expr) : Expr {
    override fun toString() = "$first > $second"
}

class Elevation(val base: Expr, val exp: Expr) : Expr {
    override fun toString() = "($base^$exp)"
}

class Sum(val first: Expr, val second: Expr) : Expr {
    override fun toString() = "$first + $second"
}

class Minus(val first: Expr, val second: Expr) : Expr {
    override fun toString() = "$first - $second"
}

class Multiply(val first: Expr, val second: Expr) : Expr {
    override fun toString() = "$first * $second"
}

class IfThenElse(val statement: Expr, val then: Expr, val elze: Expr) : Expr {
    override fun toString() = "if ($statement) then $then else $elze"
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