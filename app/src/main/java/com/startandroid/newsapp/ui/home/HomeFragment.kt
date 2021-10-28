package com.startandroid.newsapp.ui.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.startandroid.newsapp.R
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.startandroid.newsapp.data.model.PopularNewsItem
import com.startandroid.newsapp.data.model.StoriesNewsItem
import com.startandroid.newsapp.ui.home.adapter.PageAdapter
import com.startandroid.newsapp.ui.main.MainContract
import com.startandroid.newsapp.utils.IOnBackPressed


class HomeFragment : Fragment(), IOnBackPressed {

    private lateinit var tlTabsFragments: TabLayout
    private lateinit var vpPagerFragments: ViewPager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeView: View = inflater.inflate(R.layout.fr_home_screen, container, false)

        vpPagerFragments = homeView.findViewById(R.id.vpPagerFragments)
        vpPagerFragments.adapter = PageAdapter(requireActivity().supportFragmentManager)


        tlTabsFragments = homeView.findViewById(R.id.tlTabsFragments)
        tlTabsFragments.setupWithViewPager(vpPagerFragments, false)

        return homeView
    }

    override fun onBackPressed(): Boolean {
        val manager: FragmentManager = (context as AppCompatActivity)
            .supportFragmentManager
        if (manager.backStackEntryCount > 0) {
            requireActivity().finish()
        }
        return true
    }

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        Log.d("Back_Stack", "onAttach: HomeFragment")
//    }
//
//    override fun onStart() {
//        super.onStart()
//        Log.d("Back_Stack", "onStart: HomeFragment")
//    }
//
//    override fun onPause() {
//        super.onPause()
//        Log.d("Back_Stack", "onPause: HomeFragment")
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        Log.d("Back_Stack", "onDestroyView: HomeFragment")
//    }
//
//    override fun onDetach() {
//        super.onDetach()
//        Log.d("Back_Stack", "onDetach: HomeFragment")
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        Log.d("Back_Stack", "onDestroy: HomeFragment")
//    }
}