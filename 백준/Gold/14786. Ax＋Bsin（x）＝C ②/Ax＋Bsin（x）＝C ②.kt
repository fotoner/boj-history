import kotlin.math.cos
import kotlin.math.sin

fun readLn() = readLine()!!
fun readStrings() = readLn().split(" ")
fun readDoules() = readStrings().map { it.toDouble() }

fun f(x: Double, a:Double, b:Double, c:Double):Double = a * x + b * sin(x) - c
fun df(x: Double, a:Double, b:Double):Double = a + b * cos(x)


fun main(){
    val (a, b, c) = readDoules()

    var x: Double = (a + c) / 2

    for (i in 0..20000) {
        x = x - f(x, a, b, c) / df(x, a, b)
    }
    println(x)
}