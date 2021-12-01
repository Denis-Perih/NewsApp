package com.startandroid.newsapp.ui.main

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.startandroid.newsapp.R
import com.startandroid.newsapp.data.application.NewsApplication
import com.startandroid.newsapp.data.application.Screens
import com.startandroid.newsapp.databinding.ActivityMainBinding
import com.startandroid.newsapp.utils.IOnBackPressed
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject

@InternalCoroutinesApi
@RequiresApi(Build.VERSION_CODES.M)
class MainActivity : AppCompatActivity() {

    private var bind: ActivityMainBinding? = null
    private val binding get() = bind!!

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val navigator =  AppNavigator(this, R.id.mainFragmentContainer)

    override fun onCreate(savedInstanceState: Bundle?) {
        NewsApplication.INSTANCE.appComponent.inject(this)
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

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
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
        router.newRootScreen(Screens.SplashScreen())
    } else {
        binding.mainFragmentContainer.visibility = View.INVISIBLE
        binding.srlNoNetConnection.visibility = View.VISIBLE
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onDestroy() {
        bind = null
        super.onDestroy()
    }
}