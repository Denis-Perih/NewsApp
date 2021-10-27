package com.startandroid.newsapp.ui.main

import android.content.Context
import android.content.Intent
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
import com.startandroid.newsapp.data.model.PopularNewsItem
import com.startandroid.newsapp.data.model.StoriesNewsItem
import com.startandroid.newsapp.ui.home.HomeFragment
import com.startandroid.newsapp.ui.home.IOnBackPressed
import com.startandroid.newsapp.ui.more.MoreItemFragment
import com.startandroid.newsapp.ui.signin.view.SignInFragment
import com.startandroid.newsapp.ui.splash.SplashFragment

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

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.mainFragmentContainer)
        if (fragment !is IOnBackPressed || !(fragment as IOnBackPressed).onBackPressed()) {
            super.onBackPressed()

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.mainFragmentContainer)
        currentFragment?.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun isNetConnected(): Boolean {
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

    private fun openFragment(newFragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
            .replace(R.id.mainFragmentContainer, newFragment, newFragment::class.java.simpleName)
            .addToBackStack(newFragment::class.java.simpleName)

        transaction.commit()
    }

    override fun openHomeFragment() {
        openFragment(HomeFragment())
    }

    override fun openSignInFragment() {
        openFragment(SignInFragment())
    }

    override fun noNetConnected() {
        mainFragmentContainer.visibility = View.INVISIBLE
        srlNoNetConnection.visibility = View.VISIBLE
    }

    override fun openPopularNewsMoreFragment(popularNewsItem: PopularNewsItem) {

        val bundle = Bundle()
        bundle.putParcelable("popularNewsItem", popularNewsItem)

        val fragment: Fragment = MoreItemFragment()
        fragment.arguments = bundle

        openFragment(fragment)
    }

    override fun openTopStoriesMoreFragment(storiesNewsItem: StoriesNewsItem) {
        val bundle = Bundle()
        bundle.putParcelable("storiesNewsItem", storiesNewsItem)

        val fragment: Fragment = MoreItemFragment()
        fragment.arguments = bundle

        openFragment(fragment)
    }
}