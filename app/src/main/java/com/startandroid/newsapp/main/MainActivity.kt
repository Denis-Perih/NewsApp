package com.startandroid.newsapp.main

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.FragmentTransaction
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.startandroid.newsapp.R
import com.startandroid.newsapp.home.HomeFragment
import com.startandroid.newsapp.signin.SignInFragment
import com.startandroid.newsapp.splash.SplashFragment

@RequiresApi(Build.VERSION_CODES.M)
class MainActivity : AppCompatActivity(), MainContract {

    private lateinit var srlNoNetConnection: SwipeRefreshLayout
    private lateinit var mainFragmentContainer: FragmentContainerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainFragmentContainer = findViewById(R.id.mainFragmentContainer)
        srlNoNetConnection = findViewById(R.id.srlNoNetConnection)
        with(srlNoNetConnection) {
            this.setOnRefreshListener {
                if (isNetConnected()) {
                    startApp()
                }
                isRefreshing = false
            }
        }

        startApp()
    }

    fun isNetConnected(): Boolean {
        val connectManager =
            baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectManager != null) {
            val capabilities =
                connectManager.getNetworkCapabilities(connectManager.activeNetwork)
            if (capabilities != null) {
                return true
            }
        }
        return false
    }

    fun startApp() = if (isNetConnected()) {
        srlNoNetConnection.visibility = View.INVISIBLE
        mainFragmentContainer.visibility = View.VISIBLE
        openFragment(SplashFragment())
    } else {
        mainFragmentContainer.visibility = View.INVISIBLE
        srlNoNetConnection.visibility = View.VISIBLE
    }

    fun openFragment(newFragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
            .replace(R.id.mainFragmentContainer, newFragment)
            .addToBackStack(null)

        transaction.commit()
    }

    override fun openHomeFragment() {
        openFragment(HomeFragment())
    }

    override fun openSignInFragment() {
        openFragment(SignInFragment())
    }


}