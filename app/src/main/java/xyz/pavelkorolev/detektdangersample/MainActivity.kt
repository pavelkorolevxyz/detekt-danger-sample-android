package xyz.pavelkorolev.detektdangersample

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import xyz.pavelkorolev.library.Greeting

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(
        savedInstanceState: Bundle?,
        persistentState: PersistableBundle?,
    ) {
        super.onCreate(savedInstanceState, persistentState)
        Greeting().sayHello(this)
    }
}
