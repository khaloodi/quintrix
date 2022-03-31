/**
 * Program that implements classes for different kinds of dwellings.
 * Shows how to:
 * Create class hierarchy, variables and functions with inheritance,
 * abstract class, overriding, and private vs. public variables.
 */

import kotlin.math.PI
import kotlin.math.sqrt

fun main() {
//    val squareCabin = SquareCabin(6)
    val squareCabin = SquareCabin(6, 50.0)
//    val roundHut = RoundHut(3)
    val roundHut = RoundHut(3, 10.0)
//    val roundTower = RoundTower(4)
    val roundTower = RoundTower(4, 15.5)

    with(squareCabin) {
        println("\nSquare Cabin\n============")
        println("Capacity: ${capacity}")
        println("Material: ${buildingMaterial}")
        println("Has room? ${hasRoom()}")
        println("Floor area: ${floorArea()}")
    }

    with(roundHut) {
        println("\nRound Hut\n=========")
        println("Material: ${buildingMaterial}")
        println("Capacity: ${capacity}")
        println("Has room? ${hasRoom()}")
        // println("Floor area: ${floorArea()}")
        println("Floor area: %.2f".format(floorArea())) // format to only show two decimal places
        println("Has room? ${hasRoom()}")
        getRoom()
        println("Has room? ${hasRoom()}")
        getRoom()
        // println("Carpet size: ${calculateMaxCarpetSize()}")
        println("Carpet size: %.2f".format(calculateMaxCarpetSize()))
    }

    with(roundTower) {
        println("\nRound Tower\n==========")
        println("Material: ${buildingMaterial}")
        println("Capacity: ${capacity}")
        println("Has room? ${hasRoom()}")
        // println("Floor area: ${floorArea()}")
        println("Floor area: %.2f".format(floorArea())) // format to only show two decimal places
        // println("Carpet size: ${calculateMaxCarpetSize()}")
        println("Carpet size: %.2f".format(calculateMaxCarpetSize()))
    }
}

/**
 * Defines properties common to all dwellings.
 * All dwellings have floorspace,
 * but its calculation is specific to the subclass.
 * Checking and getting a room are implemented here
 * because they are the same for all Dwelling subclasses.
 *
 * @param residents Current number of residents
 */
abstract class Dwelling(private var residents: Int) {
    abstract val buildingMaterial: String
    abstract val capacity: Int

    /**
     * Checks whether there is room for another resident.
     *
     * @return true if room available, false otherwise
     */
    fun hasRoom(): Boolean {
        return residents < capacity
    }

    /**
     * Compares the capacity to the number of residents and
     * if capacity is larger than number of residents,
     * add resident by increasing the number of residents.
     * Print the result.
     */
    fun getRoom() {
        if (capacity > residents) {
            residents++
            println("You got a room!")
        } else {
            println("Sorry, at capacity and no rooms left.")
        }
    }

    /**
     * Calculates the floor area of the dwelling.
     * Implemented by subclasses where shape is determined.
     *
     * @return floor area
     */
    abstract fun floorArea(): Double // this is an "abstract" function inside an abstract class that returns a float
    // All abstract methods defined in an abstract class must be implemented in any of its subclasses.
    // Before you can run your code, you need to implement floorArea() in the subclasses

}

/**
 * A square cabin dwelling.
 *
 *  @param residents Current number of residents
 *  @param length Length
 */
//class SquareCabin(residents: Int) : Dwelling(residents) {
class SquareCabin(residents: Int, val length: Double) : Dwelling(residents) {
    override val buildingMaterial = "Wood"
    override val capacity = 6

    /**
     * Calculates floor area for a square dwelling.
     *
     * @return floor area
     */
    override fun floorArea(): Double {
        return length * length
    }

}

/**
 * Dwelling with a circular floorspace
 *
 * @param residents Current number of residents
 * @param radius Radius
 */
//open class RoundHut(residents: Int) : Dwelling(residents) { // marking this with the open keyword always it to be
open class RoundHut(residents: Int,
                    val radius: Double) : Dwelling(residents) { // marking this with the open keyword allows it to be
                    // inherited by the RoundTower Class
    override val buildingMaterial = "Straw"
    override val capacity = 4

    /**
     * Calculates floor area for a round dwelling.
     *
     * @return floor area
     */
    override fun floorArea(): Double {
        return PI * radius * radius
    }

    /**
     *  Calculates the max length for a square carpet
     *  that fits the circular floor.
     *
     * @return length of carpet
     */
    fun calculateMaxCarpetSize(): Double {
        val diameter = 2 * radius
        return sqrt(diameter * diameter / 2)
    }

}

/**
 * Round tower with multiple stories.
 *
 * @param residents Current number of residents
 * @param radius Radius
 * @param floors Number of stories
 */
/* Create a RoundTower class that is a subclass of RoundHut. Add the residents parameter to the
 constructor of RoundTower, and then pass that parameter to the constructor of the RoundHut superclass.
 */
class RoundTower(
    residents: Int,
    radius: Double,
    val floors: Int = 2) : RoundHut(residents, radius) { // forgetting to add radius param inside RoundHut parent class
    // causes the following error: No value passed for parameter 'radius'

    override val buildingMaterial = "Stone"

    // Capacity depends on the number of floors.
    override val capacity = 4 * floors

//    override fun floorArea(): Double {
//        return PI * radius * radius * floors
//    }

    /**
     * Calculates the total floor area for a tower dwelling
     * with multiple stories.
     *
     * @return floor area
     */
    override fun floorArea(): Double {
        return super.floorArea() * floors
    }
}