package com.example.frustratedgirlwriter.Helper

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.frustratedgirlwriter.fragments.Home.News_HomeFragment
import com.example.frustratedgirlwriter.fragments.Home.PostFragment
import com.example.frustratedgirlwriter.fragments.Home.Stories_HomeFragment

class MyAdapter(
    private val myContext: Context,
    fm: FragmentManager,
    var totalTabs: Int
) :
    FragmentPagerAdapter(fm){

    // this is for fragment tabs
        override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                //  val homeFragment: HomeFragment = HomeFragment()
                 News_HomeFragment()

            }
            1 -> {
                 Stories_HomeFragment()
            }
            2 ->{
                PostFragment()
            }
            else ->  getItem(position)
        }
    }

    // this counts total number of tabs
    override fun getCount(): Int {
        return totalTabs
    }
}