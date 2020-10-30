package com.example.frustratedgirlwriter.UI

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.frustratedgirlwriter.R
import com.example.frustratedgirlwriter.fragments.Home.HomeFragment
import com.example.frustratedgirlwriter.fragments.LibraryFragment
import com.example.frustratedgirlwriter.fragments.Notification.NotificationFragment
import com.example.frustratedgirlwriter.fragments.Profile.ProfileFragment
import com.example.frustratedgirlwriter.fragments.Shop.ShopFragment
import kotlinx.android.synthetic.main.activity_base.*


class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        //Shadow Navbar
        bottomNav.background= Drawable.createFromPath("@drawable/dialog_holo_light_frame")
        //Action Bar
        supportActionBar?.setTitle(Html.fromHtml("<font color=\"#000000\">&nbsp;&nbsp;&nbsp;Home</font>"))
        val abColor = ColorDrawable(ContextCompat.getColor(this,R.color.actionBarColorHome))
        supportActionBar?.setBackgroundDrawable(abColor)

        val homeFragment = HomeFragment()
        val notificationFragment = NotificationFragment()
        val profileFragment = ProfileFragment()
        val libraryFragment = LibraryFragment()
        val shopFragment = ShopFragment()
        setFragment(homeFragment)


        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> {
                    setContent(R.color.actionBarColorHome, "<font color=\"#000000\">&nbsp;&nbsp;&nbsp;Home</font>" )
                    bottomNav.itemBackground = ColorDrawable(ContextCompat.getColor(this,R.color.actionBarColorHome))
                    setFragment(homeFragment)
                    true
                }
                R.id.menu_notification -> {
                    setContent(R.color.actionBarColorNotification, "<font color=\"#000000\">&nbsp;&nbsp;&nbsp;Notification</font>")
                    bottomNav.itemBackground = ColorDrawable(ContextCompat.getColor(this,R.color.actionBarColorNotification))
                    setFragment(notificationFragment)
                    true
                }
                R.id.menu_library -> {
                    setContent(R.color.actionBarColorLibrary,"<font color=\"#000000\">&nbsp;&nbsp;&nbsp;Library</font>")
                    bottomNav.itemBackground = ColorDrawable(ContextCompat.getColor(this,R.color.actionBarColorLibrary))
                    setFragment(libraryFragment)
                    true
                }
                R.id.menu_profile -> {
                    setContent(R.color.actionBarColorProfile,"<font color=\"#000000\">&nbsp;&nbsp;&nbsp;Profile</font>")
                    bottomNav.itemBackground = ColorDrawable(ContextCompat.getColor(this,R.color.actionBarColorProfile))
                    setFragment(profileFragment)
                    true
                }
                R.id.menu_shop -> {
                    setContent(R.color.actionBarColorShop,"<font color=\"#000000\">&nbsp;&nbsp;&nbsp;Shop</font>")
                    bottomNav.itemBackground = ColorDrawable(ContextCompat.getColor(this,R.color.actionBarColorShop))
                    setFragment(shopFragment)
                    true
                }
                else -> false
            }
        }

    }
    private fun setContent(colorBack: Int, htmlString: String) {
        supportActionBar?.setTitle(Html.fromHtml(htmlString))
        val abColor = ColorDrawable(ContextCompat.getColor(this,colorBack))
        supportActionBar?.setBackgroundDrawable(abColor)
    }

    private fun setFragment(currentFragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_framelayout, currentFragment)
            commit()
        }
    }
}