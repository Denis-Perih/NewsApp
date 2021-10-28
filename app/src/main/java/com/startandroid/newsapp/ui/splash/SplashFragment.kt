package com.startandroid.newsapp.ui.splash

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.startandroid.newsapp.R
import com.startandroid.newsapp.databinding.FrSplashScreenBinding
import com.startandroid.newsapp.ui.main.MainContract

class SplashFragment : Fragment(R.layout.fr_splash_screen) {

    private lateinit var logoAnimation: Animation
    private lateinit var buttonAnimation: Animation

    private var bind: FrSplashScreenBinding? = null
    private val binding get() =  bind!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind = FrSplashScreenBinding.bind(view)

        logoAnimation = AnimationUtils.loadAnimation(context, R.anim.logo_anim_splash)
        buttonAnimation = AnimationUtils.loadAnimation(context, R.anim.btn_anim_splash)

        binding.btnOpenHomeFragment.setOnClickListener { (requireActivity() as MainContract).openSignInFragment() }
        binding.ivLogoSplash.animation = logoAnimation
        binding.btnOpenHomeFragment.animation = buttonAnimation
    }

    override fun onDestroyView() {
        bind = null
        super.onDestroyView()
    }
}