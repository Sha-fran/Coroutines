package com.example.coroutines

import kotlinx.coroutines.delay

object CoroutinesWorker {

    suspend fun executeWorks():String {
        val first = firstWork()
        val second = secondWork(first)
        val third = thirdWork(second)

        return fourthdWork(third)
    }

    private suspend fun firstWork():String {
        delay(1000)
        return "Work 1 is finished"
    }

    private suspend fun secondWork(previousResult:String):String {
        delay(1000)
        return "$previousResult\nWork 2 is finished"
    }

    private suspend fun thirdWork(previousResult:String):String {
        delay(1000)
        return "$previousResult\nWork 3 is finished"
    }

    private suspend fun fourthdWork(previousResult:String):String {
        delay(1000)
        return "$previousResult\nWork 4 is finished"
    }
}
