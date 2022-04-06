fun main(args: Array<String>) {

    print("enter N2: ")
    var n2: Int = readLine()!!.toInt()

    try {
        var Div = 100/n2
        println("Div: $Div")
        println("app is done")
    } catch (ex :Exception) { // syntax for writing try/catch
        println(ex.message)
    }
}