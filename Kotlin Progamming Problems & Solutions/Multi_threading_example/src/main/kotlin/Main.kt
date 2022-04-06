fun main(args: Array<String>) {
    var t1= thread("thread1")
    t1.start() //in order to make thread class operate as a thread, have to call .start()

    var t2 = thread("thread2")
    t2.start()

    var t3 = thread("thread3")
    t3.start()
    t3.join() // waits until thread 3 is done.. then continue

    // Once thread 3 is complete, the following will execute:
    println(" thread is run") // notice this line is run, as well as Thread code ... non-blocking
}

class thread: Thread {
    var ThreadName:String? = null

    constructor(ThreadName:String):super() {
        this.ThreadName = ThreadName
        println("${this.ThreadName} has started")
    }

    override fun run() {
        // Write Thread

        var count = 0

        while(count<10) {
            println("ThreadName: ${this.ThreadName} Count: $count")
            count++

            try {
                Thread.sleep(1000)
            } catch (ex: Exception) {
                println(ex.message)
            }
        }
    }
}