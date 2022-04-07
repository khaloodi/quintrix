// A hero is on his way to the castle to complete his mission. However, he's been told
// that the castle is surrounded with a couple of powerful dragons! each dragon
// takes 2 bullets to be defeated, our hero has no idea how many bullets he should carry..
// Assuming he's gonna grab a specific given number of bullets and move forward to fight another
// specific given number of dragons, will he survive?
//
//Return True if yes, False otherwise :)

fun main(args: Array<String>) {
    print(hero(6,2))
}

fun hero(bullets: Int, dragons: Int) : Boolean {
    //code here
    var numBulletsNeeded = dragons*2

    if (bullets < numBulletsNeeded) {
        return false
    } else {
        return true
    }
}