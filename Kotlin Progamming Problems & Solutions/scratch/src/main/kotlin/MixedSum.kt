
fun main(args: Array<String>) {
    print(mixedSum2(listOf(5, "5", 5)))

}

public fun mixedSum2(mixed: List<Any>): Int {
    var sum = 0
    for (num in mixed) {
        println(num)
        var num: Int = num.toString().toInt()
        sum += num
    }
    return sum
}

/**
 * Another way:
 *
public fun sum(mixed: List<Any>): Int = mixed.sumBy {
when(it) {
is Int -> it
is String -> it.toInt()
else -> 0
}
}

 * Another way:
 *
public fun sum(mixed: List<Any>) = mixed.sumBy { it.toString().toInt() }
 */
