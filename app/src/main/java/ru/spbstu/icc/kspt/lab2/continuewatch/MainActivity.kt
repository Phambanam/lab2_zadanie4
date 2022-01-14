package ru.spbstu.icc.kspt.lab2.continuewatch

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
const val  TAG: String = "my_log"
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
                    textSecondsElapsed.text = "Seconds elapsed: " + secondsElapsed++

                }
                Thread.sleep(1000)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG,"OnCreate")
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)
        sharedPreferences = getSharedPreferences(SECONDS_ELAPSED, Context.MODE_PRIVATE)
        backgroundThread.start()
        val btnActivity = findViewById<Button>(R.id.btn_activity)
        btnActivity.setOnClickListener{
           startActivity(Intent(this,DialogActiviti::class.java))

        }
    }
    override fun onStart() {
        super.onStart()
        Log.d(TAG,"onStart")
        count = true
        secondsElapsed = sharedPreferences.getInt(SECONDS_ELAPSED, 0)

    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,"onPause")

    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG,"onRestart")
    }


    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"Destroy")
    }
    override fun onResume() {
        super.onResume()
        Log.d(TAG,"OnResume")


    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,"OnStop")
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
