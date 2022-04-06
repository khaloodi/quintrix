import java.util.*
import kotlin.collections.ArrayList

class Person (var name: String, var age: Int): Comparable<Person> { // implement an interface called Comparable to turn this class into a comparable object
    override fun compareTo(other: Person): Int { // compare one object to another one
        return this.age - other.age // returns objects in ascending order by age when sorted
    }
}

fun main(args: Array<String>) {
    // var listOfNames = ArrayList<String>()
    // NOTE: "String" already has a comparable method inside that class, that allows us to compare one object to the next for sorting
    var listOfNames2 = ArrayList<Person>()

    // listOfNames.add("Walter")
    // listOfNames.add("Nada")
    // listOfNames.add("Zena")
    // listOfNames.add("Khaled")

    listOfNames2.add(Person("Walter", 73))
    listOfNames2.add(Person("Nada", 62))
    listOfNames2.add(Person("Zena", 25))
    listOfNames2.add(Person("Khaled", 30))

    println("Names before sorting: ")
    for (person in listOfNames2) {
        println("Name: " + person.name + "Age: " + person.age)
    }

    Collections.sort(listOfNames2) // error here until we override comparable method
    println("Names after sorting: ")
    for (person in listOfNames2) {
        println("Name: " + person.name + "Age: " + person.age)
    }

    /**
    Output:
    Names before sorting:
    Name: WalterAge: 73
    Name: NadaAge: 62
    Name: ZenaAge: 25
    Name: KhaledAge: 30
    Names after sorting:
    Name: ZenaAge: 25
    Name: KhaledAge: 30
    Name: NadaAge: 62
    Name: WalterAge: 73
     */
}