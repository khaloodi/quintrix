fun main(args: Array<String>) {

    val guestsPerFamily = listOf(2,4,1,3)
    var totalGuests = 0
    var index = 0

    println("guestsPerFamily size is: ${guestsPerFamily.size}")

    while(index < guestsPerFamily.size) {
        totalGuests += guestsPerFamily[index]
        println(index)
        index++
    }

    println("Total guest count: $totalGuests")

    val names = listOf("Jessica", "Henry", "Alicia", "Jose")
    for (name in names) {
        println(name)
    }
    // Modify the loop to also print out the number of characters in that person's name.
    // Hint: you can use the length property of a String to find the number of characters
    // in that String
    val names1 = listOf("Jessica", "Henry", "Alicia", "Jose")
    for (name in names1) {
        println("$name - Number of characters: ${name.length}")
    }
    // The code in the loop did not change the original List. It only affected what was printed.

}