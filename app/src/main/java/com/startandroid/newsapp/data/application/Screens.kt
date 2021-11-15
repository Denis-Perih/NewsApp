package com.startandroid.newsapp.data.application

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.startandroid.newsapp.data.model.PopularNewsItem
import com.startandroid.newsapp.data.model.StoriesNewsItem
import com.startandroid.newsapp.ui.home.HomeFragment
import com.startandroid.newsapp.ui.more.MoreItemFragment
import com.startandroid.newsapp.ui.signin.view.SignInFragment
import com.startandroid.newsapp.ui.splash.SplashFragment
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
object Screens {
    fun SplashScreen() = FragmentScreen { SplashFragment() }

    fun SignInScreen() = FragmentScreen { SignInFragment() }

    fun HomeScreen() = FragmentScreen { HomeFragment() }

    fun MoreItemScreen(item: PopularNewsItem) = FragmentScreen { MoreItemFragment.newInstance(item) }

    fun MoreItemScreen(item: StoriesNewsItem) = FragmentScreen { MoreItemFragment.newInstance(item) }
}