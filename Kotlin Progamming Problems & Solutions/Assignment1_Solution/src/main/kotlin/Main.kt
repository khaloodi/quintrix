fun main(args: Array<String>) {
    val nameNumMap = mapOf("Abe" to 323532342,
        "Ben" to 234295435,
        "Cathy" to 939534012,
        "Don" to 202934543,
        "Elle" to 330394011,
        "Frances" to 839047202,
        "Gaby" to 901239409)

    val numPurchList = listOf(323532342 to 52.81,
        234295435 to 61.62,
        939534012 to 91.11,
        202934543 to 29.54,
        330394011 to 29.82,
        839047202 to 14.34,
        901239409 to 3.10,
        939534012 to 53.92,
        202934543 to 68.73,
        330394011 to 35.94,
        839047202 to 7.55,
        901239409 to 2.70)
    printPurchases(nameNumMap, numPurchList)
}

//function that takes in the map and the list of purchases
fun printPurchases(nameNum : Map<String, Int> , numPurch : List<Pair<Int , Double>>) {
    //iterate through keys in the map which are the names of the customers
    for(currName in nameNum.keys){ //outer loop #1
        //for the key, get the value, which is the account number from the Set
        var currNum = nameNum.getValue(currName)
        //initialize the total to zero for each customer
        var currTotal = 0.0
        //iterate through list of purchases
        for(currPair in numPurch){
            //if account number from list = account number from outer loop #1
            if(currPair.first == currNum){
                //add to the purchase total
                currTotal += currPair.second
            }
        }
        //print total for 1 customer below, then move to outer loop for next customer
        println("$currName total purchases =  $currTotal")
    }

}