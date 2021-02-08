package me.inassar.androidnotifications

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import me.inassar.androidnotifications.ui.EggTimerFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, EggTimerFragment.newInstance())
                .commitNow()
        }
    }
}