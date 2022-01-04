package ir.fallahpoor.workerthread

import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.atomic.AtomicBoolean

class HandcraftedWorkerThread : Thread() {

    private val isAlive: AtomicBoolean = AtomicBoolean(true)
    private val taskQueue: ConcurrentLinkedQueue<Runnable> = ConcurrentLinkedQueue()

    init {
        start()
    }

    override fun run() {
        while (isAlive.get()) {
            if (taskQueue.isNotEmpty()) {
                val task: Runnable = taskQueue.poll()
                task.run()
            }
        }
    }

    fun execute(task: Runnable) {
        taskQueue.add(task)
    }

    fun quit() {
        isAlive.set(false)
    }

}
