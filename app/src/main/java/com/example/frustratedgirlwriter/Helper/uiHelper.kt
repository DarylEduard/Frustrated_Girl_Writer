package com.example.frustratedgirlwriter.Helper

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class uiHelper () {

    fun showLogCat (message: String){
        Log.d("Results", message)
    }

    fun currentUser_uid(): String{
        Firebase.auth.currentUser.let {
            val uid = FirebaseAuth.getInstance().uid.toString()
            return uid
        }

    }
    fun verifyCurrentUser_uid(): Boolean{
        Firebase.auth.currentUser.let {
            val uid_verify = FirebaseAuth.getInstance().uid.toString()
            if (uid_verify == "r6Jn62l2z3h5kCQ23tqjbMd2xCr2" || uid_verify == "AGMExl3VSDgwS38jHV1rWsyMyjC3") {
                return true
            }
            else{
                return false
            }
        }
    }

    fun currentUser_logout(){
        FirebaseAuth.getInstance().signOut()
    }


}