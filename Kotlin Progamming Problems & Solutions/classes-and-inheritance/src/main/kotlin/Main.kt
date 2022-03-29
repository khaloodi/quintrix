fun main(args: Array<String>) {

    abstract class Dwelling(private var residents: Int) {
        abstract val buildingMaterial: String
        abstract val capacity: Int

        fun hasRoom(): Boolean {
            return residents < capacity
        }
    }

    class SquareCabin(residents: Int) : Dwelling(residents) {
        override val buildingMaterial = "Wood"
        override val capacity = 6
    }

    val squareCabin = SquareCabin(6)

    println("\nSquare Cabin\n============")
    println("Capacity: ${squareCabin.capacity}")
    println("Material: ${squareCabin.buildingMaterial}")
    println("Has room? ${squareCabin.hasRoom()}")

}