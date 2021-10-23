package com.startandroid.newsapp.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.startandroid.newsapp.R
import com.startandroid.newsapp.ui.main.MainContract

class SplashFragment : Fragment() {

    private lateinit var logoAnimation: Animation
    private lateinit var buttonAnimation: Animation
    private lateinit var ivLogoSplash: ImageView
    private lateinit var btnOpenHomeFragment: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewSplash: View = inflater.inflate(R.layout.fr_splash_screen, container, false)

        logoAnimation = AnimationUtils.loadAnimation(context, R.anim.logo_anim_splash)
        buttonAnimation = AnimationUtils.loadAnimation(context, R.anim.btn_anim_splash)

        ivLogoSplash = viewSplash.findViewById(R.id.ivLogoSplash)
        btnOpenHomeFragment = viewSplash.findViewById(R.id.btnOpenHomeFragment)
        btnOpenHomeFragment.setOnClickListener { (requireActivity() as MainContract).openSignInFragment() }

        ivLogoSplash.animation = logoAnimation
        btnOpenHomeFragment.animation = buttonAnimation

        return viewSplash
    }
}