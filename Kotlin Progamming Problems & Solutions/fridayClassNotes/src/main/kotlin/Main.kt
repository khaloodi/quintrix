fun main(args: Array<String>) {
    fun String.singleQuotes()="'$this'"

    // println("Hello World!")
    val x:String = "Hi"
    var y = x.singleQuotes()
    println(y)
}