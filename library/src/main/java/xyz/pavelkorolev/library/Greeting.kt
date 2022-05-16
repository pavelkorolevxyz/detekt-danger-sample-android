package xyz.pavelkorolev.library

import android.content.Context
import android.widget.Toast

class Greeting {

    fun sayHello(context: Context) { Toast.makeText(context,
        "Hello, world!", Toast.LENGTH_SHORT).show()
    }
}
