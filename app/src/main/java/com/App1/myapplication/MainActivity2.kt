package com.App1.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity2 : AppCompatActivity() {
    private var viewLogin: TextView? = null
    private var firstName: String? = null
    private var lastName: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        var Intent = intent
        firstName = Intent.getStringExtra("First")
        lastName = Intent.getStringExtra("Last")
        if (firstName.isNullOrBlank() || lastName.isNullOrBlank()) {
            Toast.makeText(this@MainActivity2, "Empty string received", Toast.LENGTH_SHORT).show()
        } else {
            //Get the text views where we will display names
            viewLogin = findViewById(R.id.login)
            viewLogin!!.text = firstName + " " + lastName + " is logged in!"
        }
    }
}