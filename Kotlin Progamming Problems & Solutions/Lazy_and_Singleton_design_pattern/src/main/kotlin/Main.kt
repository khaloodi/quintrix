/**
 * Notes:
 * instead of using "static" keyword, Kotlin has this Singleton design pattern
 * this initializes and object using the lazy keyword which treats instantiation like an anonymous function
 * this allows you to share variable and methods defined once, with several other objects
 * as well as the "companion object" which defines our Singleton
 */

class Singleton{
    var name: String? = null
    private constructor() { //note the private keyword which is required for singleton design pattern
        // prevents anyone from creating a direct instance from this class
        println("instance created")
    }

    companion object {
        val instance: Singleton by lazy { Singleton() } // lazy takes in a lambda and returns an object
        // lazy function will only run our constructor once, creating or instantiating our Singleton object only once
    }

}

fun main() {


    // var s1 = Singleton()
    var s1 = Singleton.instance // have to use .instance now w/the companion object
    s1.name = "khaled"
    println(s1.name)

    var s2 = Singleton.instance
    println(s2.name)

    var s3 = Singleton.instance
    println(s3.name)

    // notice only one object is created... "instance created" only appears once in output
}