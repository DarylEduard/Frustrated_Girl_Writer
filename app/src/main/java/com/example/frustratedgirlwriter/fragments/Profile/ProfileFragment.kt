package com.example.frustratedgirlwriter.fragments.Profile

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.frustratedgirlwriter.Helper.uiHelper
import com.example.frustratedgirlwriter.LoginForms.LoginActivity
import com.example.frustratedgirlwriter.R
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : Fragment(R.layout.fragment_profile) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        logout_button.setOnClickListener {
            uiHelper().currentUser_logout()
            val intent = Intent(activity, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)

        }
    }
}