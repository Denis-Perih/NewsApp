package com.startandroid.newsapp.ui.signin.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.github.terrakok.cicerone.Router
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.material.snackbar.Snackbar
import com.startandroid.newsapp.R
import com.startandroid.newsapp.data.application.NewsApplication
import com.startandroid.newsapp.data.application.Screens
import com.startandroid.newsapp.databinding.FrSignInScreenBinding
import com.startandroid.newsapp.ui.signin.factory.SignInViewModelFactory
import com.startandroid.newsapp.ui.signin.viewmodel.SignInViewModel
import com.startandroid.newsapp.utils.IOnBackPressed
import com.startandroid.newsapp.utils.Status
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject


@InternalCoroutinesApi
class SignInFragment : Fragment(R.layout.fr_sign_in_screen), IOnBackPressed {

    private var bind: FrSignInScreenBinding? = null
    private val binding get() = bind!!

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var signInViewModelFactory: SignInViewModelFactory

    private val signInViewModel: SignInViewModel by viewModels { signInViewModelFactory }

    private lateinit var signInClient: GoogleSignInClient

    companion object {
        const val NEWS_SIGN_IN = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        NewsApplication.INSTANCE.appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind = FrSignInScreenBinding.bind(view)

        setupUI()
        setGoogleAuth()
        observeGoogleAccount()
    }

    private fun setupUI() {
        binding.btnEnterNameSignIn.setOnClickListener {
            router.newRootScreen(Screens.HomeScreen())
        }
        binding.btnGoogleSignIn.setOnClickListener {
            requireActivity().startActivityForResult(signInClient.signInIntent, NEWS_SIGN_IN)
        }
    }

    private fun setGoogleAuth() {
        signInViewModel.getGSO().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    signInClient = GoogleSignIn.getClient(activity, it.data)
                    Log.d("TAGTEG", "setGoogleAuth: " + it.data)
                }
                Status.ERROR -> {

                }
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        signInViewModel.onActivityResult(requestCode, resultCode, data)
    }

    private fun observeGoogleAccount() {
        signInViewModel.getGoogleAccount().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    //updateUI
                    router.newRootScreen(Screens.HomeScreen())
                }
                Status.ERROR -> {
                    Snackbar.make(
                        binding.clFragmentLoginScreen,
                        "error account",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        })
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