import kotlinx.coroutines.*

fun main(args: Array<String>) = runBlocking {
    val job = launch {
        repeat(1000) {i->
            println("job: I'm sleeping $i ...")
            delay(500L)
        }
    }

    delay(1300L) // delay a bit
    println("main: I'm tired of waiting!")
    job.cancel() // cancels the job
    job.join() // wiats for job's completion
    println("main: Now I can quit.")
}