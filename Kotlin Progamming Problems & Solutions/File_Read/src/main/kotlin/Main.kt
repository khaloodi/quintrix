import java.io.FileWriter
import java.io.Writer

fun main(args: Array<String>) {
    print("Write to text file: ")
    var str:String = readLine().toString()
    WriteToFile(str)
}

fun WriteToFile(str: String) {
    try {
        var fo = FileWriter("test.txt", true)
        fo.write(str+ "\n")
        fo.close()
    } catch (ex: Exception) {
        println(ex.message)
    }
}
