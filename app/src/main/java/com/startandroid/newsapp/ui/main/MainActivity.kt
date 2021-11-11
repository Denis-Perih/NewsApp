package com.startandroid.newsapp.ui.main

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.startandroid.newsapp.R
import com.startandroid.newsapp.data.model.PopularNewsItem
import com.startandroid.newsapp.data.model.StoriesNewsItem
import com.startandroid.newsapp.databinding.ActivityMainBinding
import com.startandroid.newsapp.ui.home.HomeFragment
import com.startandroid.newsapp.ui.more.MoreItemFragment
import com.startandroid.newsapp.ui.signin.view.SignInFragment
import com.startandroid.newsapp.ui.splash.SplashFragment
import com.startandroid.newsapp.utils.IOnBackPressed
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@RequiresApi(Build.VERSION_CODES.M)
class MainActivity : AppCompatActivity(), MainContract {

    private var bind: ActivityMainBinding? = null
    private val binding get() = bind!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding.srlNoNetConnection) {
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
//        val connectManager =
//            baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        if (connectManager != null) {
//            val capabilities =
//                connectManager.getNetworkCapabilities(connectManager.activeNetwork)
//            if (capabilities != null) {
//                return true
//            }
//        }
//        return false
        return true
    }

    fun startApp() = if (isNetConnected()) {
        binding.srlNoNetConnection.visibility = View.INVISIBLE
        binding.mainFragmentContainer.visibility = View.VISIBLE
        openSimpleFragment(SplashFragment())
    } else {
        binding.mainFragmentContainer.visibility = View.INVISIBLE
        binding.srlNoNetConnection.visibility = View.VISIBLE
    }

    private fun openSimpleFragment(newFragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
            .replace(R.id.mainFragmentContainer, newFragment, newFragment::class.java.simpleName)
            .addToBackStack(newFragment::class.java.simpleName)

        transaction.commit()
    }

    private fun openMoreFragment(newFragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
            .add(R.id.mainFragmentContainer, newFragment, newFragment::class.java.simpleName)
            .addToBackStack(newFragment::class.java.simpleName)

        transaction.commit()
    }

    override fun openHomeFragment() {
        openSimpleFragment(HomeFragment())
    }

    override fun openSignInFragment() {
        openSimpleFragment(SignInFragment())
    }

    override fun noNetConnected() {
        binding.mainFragmentContainer.visibility = View.INVISIBLE
        binding.srlNoNetConnection.visibility = View.VISIBLE
    }

    override fun openPopularNewsMoreFragment(popularNewsItem: PopularNewsItem) {

        val bundle = Bundle()
        bundle.putParcelable("popularNewsItem", popularNewsItem)

        val fragment: Fragment = MoreItemFragment()
        fragment.arguments = bundle

        openMoreFragment(fragment)
    }

    override fun openTopStoriesMoreFragment(storiesNewsItem: StoriesNewsItem) {
        val bundle = Bundle()
        bundle.putParcelable("storiesNewsItem", storiesNewsItem)

        val fragment: Fragment = MoreItemFragment()
        fragment.arguments = bundle

        openMoreFragment(fragment)
    }

    override fun onDestroy() {
        bind = null
        super.onDestroy()
    }
}