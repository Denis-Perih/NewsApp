package com.startandroid.newsapp.ui.splash

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.Router
import com.startandroid.newsapp.R
import com.startandroid.newsapp.data.application.NewsApplication
import com.startandroid.newsapp.data.application.Screens
import com.startandroid.newsapp.databinding.FrSplashScreenBinding
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject

@InternalCoroutinesApi
class SplashFragment : Fragment(R.layout.fr_splash_screen) {

    private lateinit var logoAnimation: Animation
    private lateinit var buttonAnimation: Animation

    private var bind: FrSplashScreenBinding? = null
    private val binding get() = bind!!

    @Inject
    lateinit var router: Router

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        NewsApplication.INSTANCE.appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)
        bind = FrSplashScreenBinding.bind(view)

        logoAnimation = AnimationUtils.loadAnimation(context, R.anim.logo_anim_splash)
        buttonAnimation = AnimationUtils.loadAnimation(context, R.anim.btn_anim_splash)

        binding.btnOpenHomeFragment.setOnClickListener { router.newRootScreen(Screens.SignInScreen()) }
        binding.ivLogoSplash.animation = logoAnimation
        binding.btnOpenHomeFragment.animation = buttonAnimation
    }

    override fun onDestroyView() {
        bind = null
        super.onDestroyView()
    }
}