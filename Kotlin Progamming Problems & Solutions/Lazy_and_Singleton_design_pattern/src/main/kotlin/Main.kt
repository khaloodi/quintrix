
class Singleton{
    var name: String? = null
    constructor() {
        println("instance created")
    }

}

fun main() {
    var s1 = Singleton()
    s1.name = "khaled"
    println(s1.name)

    var s2 = Singleton()
    println(s2.name)
}