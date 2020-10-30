package com.example.frustratedgirlwriter.fragments.Home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.frustratedgirlwriter.Helper.MyAdapter
import com.example.frustratedgirlwriter.Helper.uiHelper
import com.example.frustratedgirlwriter.R
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(R.layout.fragment_home) {

    // This is to Create View
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_home, container, false)
//    }


    //On Create class
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//    }

    var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null

    var accountType: Boolean = false


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            tabLayoutInterface()


    }


    private fun tabLayoutInterface(){
        tabLayout = home_tablayout
        viewPager = home_viewPager

        if(uiHelper().verifyCurrentUser_uid()){
            tabLayout!!.addTab(tabLayout!!.newTab().setText("Post"))
            tabLayout!!.tabGravity = TabLayout.GRAVITY_FILL
        }
        val appContext = context!!.applicationContext
        val adapter = MyAdapter(appContext, childFragmentManager!! , tabLayout!!.tabCount)

        viewPager!!.adapter = adapter
        viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout!!.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager!!.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
            override fun onTabReselected(tab: TabLayout.Tab) {

            }

        })
    }




}