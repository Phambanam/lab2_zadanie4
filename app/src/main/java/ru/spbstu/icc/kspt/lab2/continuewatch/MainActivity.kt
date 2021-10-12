package ru.spbstu.icc.kspt.lab2.continuewatch

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
const val SECONDS_ELAPSED = "seconds elapsed"
class MainActivity : AppCompatActivity() {
    var secondsElapsed: Int = 0
    var count: Boolean = true;
    lateinit var textSecondsElapsed: TextView
    var backgroundThread = Thread {

        while (true) {
            if (count) {
                textSecondsElapsed.post {
                    textSecondsElapsed.setText("Seconds elapsed: " + secondsElapsed++)

                }
                Thread.sleep(1000)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)
        backgroundThread.start()
    }

    override fun onResume() {
        count = true
        super.onResume()
    }

    override fun onStop() {
        count = false
        super.onStop()

    }

    override fun onSaveInstanceState(outState: Bundle) {
            outState.putInt(SECONDS_ELAPSED, secondsElapsed)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        secondsElapsed = savedInstanceState.getInt(SECONDS_ELAPSED)
        super.onRestoreInstanceState(savedInstanceState)
    }
}
