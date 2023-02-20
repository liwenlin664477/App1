package com.App1.myapplication

import android.app.Activity
import android.content.ActivityNotFoundException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.content.pm.ActivityInfo
import android.provider.MediaStore
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import android.os.Environment


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var firstName: String? = null
    private var lastName: String? = null
    private var middleName: String? = null
    private var submitButton: Button? = null
    private var viewFirstName: EditText? = null
    private var viewMiddleName: EditText? = null
    private var viewLastName: EditText? = null
    private var pictureButton: Button? = null
    private var pictureView: Bitmap?= null
    private var picView: ImageView? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        submitButton = findViewById(R.id.button)
        submitButton!!.setOnClickListener(this)
        pictureButton = findViewById(R.id.button_2)
        pictureButton!!.setOnClickListener(this)
        picView = findViewById(R.id.image_1)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.button -> {

                //First, get the string from the EditText
                viewFirstName = findViewById(R.id.firstName)
                viewLastName = findViewById(R.id.lastName)
                viewMiddleName = findViewById(R.id.middleName)
                firstName = viewFirstName!!.text.toString()
                lastName = viewLastName!!.text.toString()
                middleName = viewMiddleName!!.text.toString()

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
                    messageIntent.putExtra("Middle",middleName)
                    this.startActivity(messageIntent)
                }
            }
            R.id.button_2 -> {
                val pictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                try{
                    cameraActivity.launch(pictureIntent)
                }catch (e: ActivityNotFoundException){
                Toast.makeText(this,"Error: "+ e.localizedMessage,Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private val cameraActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result ->
        if(result.resultCode == RESULT_OK) {
            pictureView = result.data!!.extras!!["data"] as Bitmap?
            if (isExternalStorageWritable) {
                picView!!.setImageBitmap(pictureView)
            }
            else{
                Toast.makeText(this, "External storage not writable.", Toast.LENGTH_SHORT).show()
            }


        }
    }

    private val isExternalStorageWritable: Boolean
        get() {
            val state = Environment.getExternalStorageState()
            return Environment.MEDIA_MOUNTED == state
        }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("pic", pictureView)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {

        super.onRestoreInstanceState(savedInstanceState)
        pictureView = savedInstanceState.getParcelable("pic");
        picView!!.setImageBitmap(pictureView)
    }
}