open class Item(val name: String, val price: Int)

class Noodles : Item("Noodles", 10) {
    override fun toString(): String {
        return name
    }
}

// Update the Vegetables class header to take in 3 string parameters
// class Vegetables(val toppings: List<String>) : Item("Vegetables", 5) {
class Vegetables(vararg val toppings: String) : Item("Vegetables", 5) {
    override fun toString(): String {
        if (toppings.isEmpty()) {
            return "$name Chef's Choice"
        } else {
            return name + " " + toppings.joinToString()
        }
    }
}

/**
 * Order class

Properties: order number, list of items

Methods: add item, add multiple items, print order summary (including price)

Focusing on properties first, what should the data type of each property be?
Should they be public or private to the class?
Should they be passed in as arguments or defined within the class?
There's multiple ways to implement this, but here is one solution.
Create a class Order that has an integer orderNumber constructor parameter.
 */
class Order(val orderNumber: Int) {
    // todo Since you may not know all the items in the order upfront,
    //  don't require the list of items to be passed in as an argument.
    //  Instead it can be declared as a top-level class variable, and initialize it as
    //  an empty MutableList that can hold elements of type Item
    private val itemList = mutableListOf<Item>()

    fun addItem(newItem: Item): Order {
        itemList.add(newItem)
        // todo instead of returning Unit (or nothing) from the addItem() and addAll()
        //  methods in Order class, return the changed Order. Kotlin provides the
        //  keyword this to reference the current object instance.
        return this
    }

    fun addAll(newItems: List<Item>): Order {
        // Implement the addAll() method next. It takes in a read-only list of items.
        // Add all of those items to the internal list of items
        itemList.addAll(newItems)
        // todo instead of returning Unit (or nothing) from the addItem() and addAll()
        //  methods in Order class, return the changed Order. Kotlin provides the
        //  keyword this to reference the current object instance.
        return this
    }

    fun print() {
        println("Order #${orderNumber}")
        var total = 0
        for (item in itemList) {
            println("${item}: $${item.price}")
            total += item.price
        }
        println("Total: $${total}")
    }
}

fun main() {
    val noodles = Noodles() // initialize object instance
    val vegetables = Vegetables("Cabbage", "Sprouts", "Onion") // initialize object instance
    val vegetables2 = Vegetables()
    println(noodles)
    println(vegetables)
    println(vegetables2)

    println()
    println("***** Testing out the Order class *****")
    println()

    val ordersList = mutableListOf<Order>()

    // Add an item to an order
    val order1 = Order(1)
    order1.addItem(Noodles())
    ordersList.add(order1)

    // Add multiple items individually
    val order2 = Order(2)
    order2.addItem(Noodles())
    order2.addItem(Vegetables())
    ordersList.add(order2)

    // Add a list of items at one time
    val order3 = Order(3)
    val items = listOf(Noodles(), Vegetables("Carrots", "Beans", "Celery"))
    order3.addAll(items)
    ordersList.add(order3)

    // Use builder pattern
    // todo In the main() function, you can now chain the calls together, as shown in the following code.
    //  This code creates a new Order and takes advantage of the Builder pattern.
    val order4 = Order(4).addItem(Noodles()).addItem(Vegetables("Cabbage", "Onion"))
    ordersList.add(order4)

    // Create and add order directly
    // todo At this point, you actually don't even need to store the order in a variable.
    //  In the main() function (before the final loop to print out the orders), create
    //  an Order directly and add it to the orderList. The code is also easier to read if
    //  each method call gets put on its own line
    ordersList.add(
        Order(5)
            .addItem(Noodles())
            .addItem(Noodles())
            .addItem(Vegetables("Spinach")))

    // Print out each order
    for (order in ordersList) {
        order.print()
        println()
    }

//    val order1 = Order(1)
//    order1.addItem(Noodles())
//    order1.print()
//
//    println()
//
//    val order2 = Order(2)
//    order2.addItem(Noodles())
//    order2.addItem(Vegetables())
//    order2.print()
//
//    println()
//
//    val order3 = Order(3)
//    val items = listOf(Noodles(), Vegetables("Carrots", "Beans", "Celery"))
//    order3.addAll(items)
//    order3.print()
}