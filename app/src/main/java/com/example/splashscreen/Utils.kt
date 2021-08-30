package com.example.splashscreen

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.makeToast(message:String){
    Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
}