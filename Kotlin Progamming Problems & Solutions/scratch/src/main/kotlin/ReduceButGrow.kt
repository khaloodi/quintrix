// Given a non-empty array of integers, return the result of multiplying the values together in order.
// Example:
// [1, 2, 3, 4] => 1 * 2 * 3 * 4 = 24

fun main(args: Array<String>) {
    val num = arrayOf<Int>(1, 2, 3, 4)

    print(grow(num))
}

fun grow(arr: Array<Int>): Int {
    var total = 1
    arr.forEach {
        total*=it
    }
    return total
}

/**
 * Examples using the reduce function:
 *
example 1:
fun grow(arr: IntArray): Int {
    return arr.reduce{acc,i -> acc*i}
}

example 2:
fun grow(arr: IntArray) = arr.reduce(Int::times)

example 3:
fun grow(arr: IntArray): Int = arr.reduce { a, b -> a * b }
 */