fun main(args: Array<String>) {
   // todo create a variable called numbers of type List<Int> because this will contain a read-only list of integers
    val numbers: List<Int> = listOf(1,2,3,4,5,6)
    //           ^type       ^kotlin standard library function
    // shorthand or inferred type:
    // val numbers = listOf(1,2,3,4,5,6)

    // Access elements of the list
    println("List: $numbers")
    println("Size: ${numbers.size}")

    println("Last index: ${numbers.size - 1}")
    println("Last element: ${numbers[numbers.size - 1]}")

    println("First: ${numbers.first()}")
    println("Last: ${numbers.last()}")

    // Use the contains() method
    println("Contains 4? ${numbers.contains(4)}")
    println("Contains 7? ${numbers.contains(7)}")

    // todo Lists are read-only
    val colors = listOf("green", "orange", "blue")
    // Remember that you cannot add or change elements in a read-only List
    // colors.add("purple")  ---- will not work on read-only list
    // colors[0] = "yellow"  ---- will not work on read-only list

    println("Reversed list: ${colors.reversed()}")
    println("List: $colors")

    // todo Create a MutableList
    //  Mutable lists are lists that can be modified after creation. You can add, remove, or change items.
    //  You can do everything you can do with read-only lists as well. Mutable lists are of type MutableList,
    //  and you can create them by calling mutableListOf()
    val entrees = mutableListOf<String>()
    // can also be written like:
    // val entrees: MutableList<String> = mutableListOf()

    println("Entrees: $entrees")

    // todo Add elements to a list
    // The add() function returns true if adding the element to the list succeeded, false otherwise

    println("Add noodles: ${entrees.add("noodles")}")
    println("Entrees: $entrees")

    println("Add spaghetti: ${entrees.add("spaghetti")}")
    println("Entrees: $entrees")

    val moreItems = listOf("ravioli", "lasagna", "fettuccine")

    // Instead of adding elements one by one using add(), you can add multiple elements at a time using addAll()
    // and pass in a list.
    println("Add list: ${entrees.addAll(moreItems)}")
    println("Entrees: $entrees")


    // todo Remove elements from a list
    //  Call remove() to remove "spaghetti" from the list. Print the list again
    println("Remove spaghetti: ${entrees.remove("spaghetti")}")
    println("Entrees: $entrees")

    // What happens if you try to remove an item that doesn't exist in the list? Try to remove "rice"
    // from the list with entrees.remove("rice")
    println("Remove item that doesn't exist: ${entrees.remove("rice")}") // it outputs false!!!
    println("Entrees: $entrees")

    // You can also specify the index of the element to remove. Use removeAt() to remove the item at index 0.
    println("Remove first element: ${entrees.removeAt(0)}")
    println("Entrees: $entrees")

    // If you want to clear the whole list, you can call clear().
    entrees.clear()
    println("Entrees: $entrees")

    // Kotlin gives you a way to check if a list is empty using isEmpty() function. Try printing out entrees.isEmpty()
    println("Empty? ${entrees.isEmpty()}")
}