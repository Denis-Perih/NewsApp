package com.startandroid.newsapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.startandroid.newsapp.R
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.startandroid.newsapp.ui.home.adapter.PageAdapter


class HomeFragment : Fragment(), IOnBackPressed {

    lateinit var tlTabsFragment: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeView: View = inflater.inflate(R.layout.fr_home_screen, container, false)

        val vpPagerFragment = homeView.findViewById<ViewPager>(R.id.vpPagerFragment)
        vpPagerFragment.adapter = PageAdapter(requireActivity().supportFragmentManager)

        tlTabsFragment = homeView.findViewById(R.id.tlTabsFragment)
        tlTabsFragment.setupWithViewPager(vpPagerFragment)

        return homeView
    }

    override fun onBackPressed(): Boolean {
        val manager: FragmentManager = (context as AppCompatActivity)
            .supportFragmentManager
        if (manager.getBackStackEntryCount() > 0) {
            requireActivity().finish()
        }
        return true
    }

}