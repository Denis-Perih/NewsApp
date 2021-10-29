package com.startandroid.newsapp.ui.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.startandroid.newsapp.R
import com.startandroid.newsapp.databinding.FrHomeScreenBinding
import com.startandroid.newsapp.ui.home.adapter.PageAdapter
import com.startandroid.newsapp.utils.IOnBackPressed

class HomeFragment : Fragment(R.layout.fr_home_screen), IOnBackPressed {

    private var bind: FrHomeScreenBinding? = null
    private val binding get() = bind!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind = FrHomeScreenBinding.bind(view)

        binding.vpPagerFragments.adapter = PageAdapter(requireActivity().supportFragmentManager)
        binding.tlTabsFragments.setupWithViewPager(binding.vpPagerFragments, false)
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

    override fun onDestroyView() {
        bind = null
        super.onDestroyView()
    }
}