package com.startandroid.newsapp.ui.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.startandroid.newsapp.R
import com.startandroid.newsapp.databinding.FrHomeScreenBinding
import com.startandroid.newsapp.ui.home.adapter.PageAdapter


class HomeFragment : Fragment(R.layout.fr_home_screen), IOnBackPressed {

    private var bind: FrHomeScreenBinding? = null
    private val binding get() = bind!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind = FrHomeScreenBinding.bind(view)

        binding.vpPagerFragment.adapter = PageAdapter(requireActivity().supportFragmentManager)
        binding.tlTabsFragment.setupWithViewPager(binding.vpPagerFragment)
    }

    override fun onBackPressed(): Boolean {
        val manager: FragmentManager = (context as AppCompatActivity)
            .supportFragmentManager
        if (manager.getBackStackEntryCount() > 0) {
            requireActivity().finish()
        }
        return true
    }

    override fun onDestroyView() {
        bind = null
        super.onDestroyView()
    }
}