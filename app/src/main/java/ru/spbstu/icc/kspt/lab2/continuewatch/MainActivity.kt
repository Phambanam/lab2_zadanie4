package ru.spbstu.icc.kspt.lab2.continuewatch

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
const val SECONDS_ELAPSED = "seconds elapsed"
class MainActivity : AppCompatActivity() {
    var secondsElapsed: Int = 0
    var count: Boolean = true
    private lateinit var sharedPreferences: SharedPreferences
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
        sharedPreferences = getSharedPreferences(SECONDS_ELAPSED, Context.MODE_PRIVATE)
        backgroundThread.start()
    }

    override fun onResume() {
        super.onResume()
        count = true
        secondsElapsed = sharedPreferences.getInt(SECONDS_ELAPSED, 0)

    }

    override fun onStop() {
        super.onStop()
        count = false
        sharedPreferences.edit().run {
            putInt(SECONDS_ELAPSED, secondsElapsed)
            apply()
        }

    }

   /* override fun onSaveInstanceState(outState: Bundle) {
            outState.putInt(SECONDS_ELAPSED, secondsElapsed)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        secondsElapsed = savedInstanceState.getInt(SECONDS_ELAPSED)
        super.onRestoreInstanceState(savedInstanceState)
    }*/
}
