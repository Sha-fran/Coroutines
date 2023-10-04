package com.example.coroutines

//CALBACK HELL

object BackgroundWorker {

    fun executeWorks(onWorkFinished:(result:String) -> Unit) {
        firstWork { firstResult ->
            secondWork(firstResult) { secondResult ->
                thirdWork(secondResult) { thirdResult ->
                    fourthWork(thirdResult) {fourthResult ->
                        onWorkFinished(fourthResult)
                    }
                }
            }
        }
    }

    private fun firstWork(onFinished:(result:String) -> Unit){
        Thread {
            Thread.sleep(1000)
            onFinished("Work 1 is finished")
        }.start()
    }

    private fun secondWork(previousResult:String, onFinished:(result:String) -> Unit){
        Thread {
            Thread.sleep(1000)
            onFinished("$previousResult\nWork 2 is finished")
        }.start()
    }

    private fun thirdWork(previousResult:String, onFinished:(result:String) -> Unit){
        Thread {
            Thread.sleep(1000)
            onFinished("$previousResult\nWork 3 is finished")
        }.start()
    }

    private fun fourthWork(previousResult:String, onFinished:(result:String) -> Unit){
        Thread {
            Thread.sleep(1000)
            onFinished("$previousResult\nWork 4 is finished")
        }.start()
    }
}
