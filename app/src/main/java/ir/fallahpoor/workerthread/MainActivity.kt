package ir.fallahpoor.workerthread

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var counter = 1
    private lateinit var workerThread: HandcraftedWorkerThread
    private val handler: Handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message?) {
            messageTextView.text = msg?.obj as String
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        workerThread = HandcraftedWorkerThread()
        //workerThread = LooperWorkerThread()
        //workerThread = HandlerWorkerThread("WorkerThread")

        runTaskButton.setOnClickListener {
            messageTextView.text = "Please wait ..."
            workerThread.execute(Runnable {
                Thread.sleep(3000)
                handler.sendMessage(createMessage("Task $counter completed"))
                counter++
            })
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        workerThread.quit()
    }

    private fun createMessage(text: String): Message {
        return Message.obtain().apply { obj = text }
    }

}
