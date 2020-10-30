package com.example.frustratedgirlwriter.LoginForms
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.frustratedgirlwriter.Helper.uiHelper
import com.example.frustratedgirlwriter.R
import com.example.frustratedgirlwriter.UI.BaseActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        signup_editText_login.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        login_button.setOnClickListener {
            performLogin()
        }

        forgotPassword_editText_login.setOnClickListener {
            val auth = Firebase.auth
            val currentUser = auth.currentUser

            currentUser.let {
                val email = currentUser?.email
                val uid = currentUser?.uid
                uiHelper().showLogCat( "User: $email, UID: $uid")


            }


        }
    }

    private fun performLogin(){
        val email = email_editText_login.text.toString()
        val password = password_editText_login.text.toString()

        if (email.isEmpty() || password.isEmpty()){

            Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            return
        }
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener() {
                if(!it.isSuccessful) {
                    Toast.makeText(this, "Incorrect username or password!", Toast.LENGTH_SHORT).show()
                    password_editText_login.text.clear()
                }
                else {
                    Toast.makeText(this, "Logged in successful!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, BaseActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(Intent(intent))
                }

            }
            .addOnFailureListener(){
                Toast.makeText(this, "Failed to create user: ${it.message}", Toast.LENGTH_SHORT).show()
            }


    }
}