fun main(args: Array<String>) {
   // todo create a variable called numbers of type List<Int> because this will contain a read-only list of integers
    val numbers: List<Int> = listOf(1,2,3,4,5,6)
    //           ^type       ^kotlin standard library function
    // shorthand or inferred type:
    // val numbers = listOf(1,2,3,4,5,6)
    println("List $numbers")
}