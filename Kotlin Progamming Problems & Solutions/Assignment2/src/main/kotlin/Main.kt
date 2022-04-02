import java.io.*
import java.math.*
import java.security.*
import java.text.*
import java.util.*
import java.util.concurrent.*
import java.util.function.*
import java.util.regex.*
import java.util.stream.*
import kotlin.collections.*
import kotlin.comparisons.*
import kotlin.io.*
import kotlin.jvm.*
import kotlin.jvm.functions.*
import kotlin.jvm.internal.*
import kotlin.ranges.*
import kotlin.sequences.*
import kotlin.text.*
/*
* Complete the 'lonelyinteger' function below.
*
* The function is expected to return an INTEGER.
* The function accepts INTEGER_ARRAY a as parameter.
*/
val duplicates: List<Int> = emptyList()

fun lonelyinteger(a: Array<Int>): Int {
// Write your code here
    a.sort()    // sort the array
    val duplicates: MutableList<Int> = mutableListOf()
    var previous: Int? = null

    for (e in a)
    {
        if (e != null && e == previous) {
            duplicates.add(e)
        }
        previous = e
    }


    return duplicates[0]
}

fun main(args: Array<String>) {
    val n = readLine()!!.trim().toInt()
    val a = readLine()!!.trimEnd().split(" ").map{ it.toInt() }.toTypedArray()
    println(a)
    val result = lonelyinteger(a)
    println(result)
}