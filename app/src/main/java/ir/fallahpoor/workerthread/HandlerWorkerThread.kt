package ir.fallahpoor.workerthread

import android.os.Handler
import android.os.HandlerThread

class HandlerWorkerThread(threadName: String) : HandlerThread(threadName) {

    private lateinit var handler: Handler

    init {
        start()
    }

    override fun onLooperPrepared() {
        handler = Handler()
    }

    fun execute(task: Runnable) {
        handler.post(task)
    }

}