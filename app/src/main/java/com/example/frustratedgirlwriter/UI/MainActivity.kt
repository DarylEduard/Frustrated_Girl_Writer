package com.example.frustratedgirlwriter.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.frustratedgirlwriter.Helper.uiHelper
import com.example.frustratedgirlwriter.LoginForms.LoginActivity
import com.example.frustratedgirlwriter.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //4second splash time

        Handler().postDelayed({
            //start main activity

                val uid = uiHelper().currentUser_uid()

                if (uid == "null") {
                    startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                    //finish this activity
                    finish()
                } else
                {
                    startActivity(Intent(this@MainActivity, BaseActivity ::class.java))
                    //finish this activity
                    finish()
                }

        },4000)
    }
}