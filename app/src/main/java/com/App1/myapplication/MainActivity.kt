package com.App1.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.os.Bundle
import android.widget.Toast
import android.content.Intent
import android.view.View
import android.widget.Button


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var firstName: String? = null
    private var lastName: String? = null
    private var submitButton: Button? = null
    private var viewFirstName: EditText? = null
    private var viewLastName: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        submitButton = findViewById(R.id.button)
        submitButton!!.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.button -> {

                //First, get the string from the EditText
                viewFirstName = findViewById(R.id.firstName)
                viewLastName = findViewById(R.id.lastName)
                firstName = viewFirstName!!.text.toString()
                lastName = viewLastName!!.text.toString()


                //Check if the EditText string is empty
                if (firstName.isNullOrBlank() || lastName.isNullOrBlank()) {
                    //Complain that there's no text
                    Toast.makeText(this@MainActivity, "Enter the first name or last name!", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    //Reward them for submitting their names
                    Toast.makeText(this@MainActivity, "Good job!", Toast.LENGTH_SHORT).show()

                    //Start an activity and pass the EditText string to it.
                    val messageIntent = Intent(this, MainActivity2::class.java)
                    messageIntent.putExtra("First", firstName)
                    messageIntent.putExtra("Last", lastName)
                    this.startActivity(messageIntent)
                }
            }
        }
    }

}