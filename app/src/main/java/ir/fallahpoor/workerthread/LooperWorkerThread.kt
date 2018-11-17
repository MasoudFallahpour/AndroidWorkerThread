package ir.fallahpoor.workerthread

import android.os.Handler
import android.os.Looper

class LooperWorkerThread(threadName: String) : Thread(threadName) {

    private lateinit var handler: Handler

    init {
        start()
    }

    override fun run() {

        // Preparing a looper on current thread.
        // The current thread is being detected implicitly
        Looper.prepare()

        // Attach the Handler to the Looper of current thread
        handler = Handler(Looper.myLooper())

        // After the following line the thread will start
        // running the message loop and will not normally
        // exit the loop unless a problem happens or you
        // quit() the looper
        Looper.loop()

    }

    fun execute(task: Runnable): LooperWorkerThread {
        handler.post(task)
        return this
    }

    fun quit() {
        Looper.myLooper()?.quit()
    }

}