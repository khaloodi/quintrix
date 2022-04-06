import java.util.*
import kotlin.collections.ArrayList

fun main(args: Array<String>) {
    var listOfNames = ArrayList<String>()

    listOfNames.add("Walter")
    listOfNames.add("Nada")
    listOfNames.add("Zena")
    listOfNames.add("Khaled")

    println("Names before sorting: ")
    for (name in listOfNames) {
        println("Name: " + name)
    }

    Collections.sort(listOfNames)
    println("Names after sorting: ")
    for (name in listOfNames) {
        println("Name: " + name)
    }
}