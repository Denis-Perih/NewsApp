package com.startandroid.newsapp.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.startandroid.newsapp.R

class HomeFragment : Fragment() {

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

}