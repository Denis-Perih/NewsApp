package com.startandroid.newsapp.ui.signin.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.material.snackbar.Snackbar
import com.startandroid.newsapp.R
import com.startandroid.newsapp.databinding.FrSignInScreenBinding
import com.startandroid.newsapp.ui.main.MainContract
import com.startandroid.newsapp.ui.signin.factory.SignInViewModelFactory
import com.startandroid.newsapp.ui.signin.viewmodel.SignInViewModel
import com.startandroid.newsapp.utils.Status

class SignInFragment : Fragment(R.layout.fr_sign_in_screen){

    private var bind: FrSignInScreenBinding? = null
    private val binding get() = bind!!

    private lateinit var signInViewModel: SignInViewModel
    private lateinit var signInClient: GoogleSignInClient

    companion object {
        const val NEWS_SIGN_IN = 0
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind = FrSignInScreenBinding.bind(view)

        setupUI()
        setupViewModel()
        setGoogleAuth()
        observeGoogleAccount()
    }

    private fun setupUI() {
        binding.btnEnterNameSignIn.setOnClickListener {
            (requireActivity() as MainContract).openHomeFragment()
        }
        binding.btnGoogleSignIn.setOnClickListener {
            requireActivity().startActivityForResult(signInClient.signInIntent, NEWS_SIGN_IN)
        }
    }

    private fun setupViewModel() {
        signInViewModel = ViewModelProviders.of(
            this,
            SignInViewModelFactory()
        ).get(SignInViewModel::class.java)
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
                    (requireActivity() as MainContract).openHomeFragment()
                }
                Status.ERROR -> {
                    Snackbar.make(binding.clFragmentLoginScreen, "error account", Snackbar.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun onDestroyView() {
        bind = null
        super.onDestroyView()
    }
}