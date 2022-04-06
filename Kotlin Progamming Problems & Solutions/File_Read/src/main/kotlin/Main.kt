import java.io.FileReader
import java.io.FileWriter
import java.io.Writer

fun main(args: Array<String>) {
    println("1- read \n2- write \n")
    var op = readLine()!!.toInt()

    when(op) {
        1 -> ReadFromFile() // ReadFromFile()
        2 -> {
            print("Write to text file: ")
            var str:String = readLine().toString()
            WriteToFile(str)
        }
    }


    // print("Write to text file: ")
    // var str:String = readLine().toString()
    // WriteToFile(str)
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

fun ReadFromFile() {
    try {
        var fin = FileReader("test.txt")
        var c:Int? // use to read the character from the file

        do {
            c=fin.read() // take ASCII code of first character and read
            print(c.toChar())

        } while (c != -1)

    } catch (ex: Exception) {
        print(ex.message) // if cannot read the file
    }


}
