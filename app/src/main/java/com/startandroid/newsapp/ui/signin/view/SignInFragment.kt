package com.startandroid.newsapp.ui.signin.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProviders
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.material.snackbar.Snackbar
import com.startandroid.newsapp.R
import com.startandroid.newsapp.ui.main.MainContract
import com.startandroid.newsapp.ui.signin.factory.SignInViewModelFactory
import com.startandroid.newsapp.ui.signin.viewmodel.SignInViewModel
import com.startandroid.newsapp.utils.Status

class SignInFragment : Fragment(){

    private lateinit var signInViewModel: SignInViewModel
    private lateinit var signInClient: GoogleSignInClient

    private lateinit var clFragmentLoginScreen: ConstraintLayout
    private lateinit var etNameSignIn: EditText
    private lateinit var btnEnterNameSignIn: Button
    private lateinit var btnGoogleSignIn: Button

    companion object {
        const val NEWS_SIGN_IN = 0
    }

    private lateinit var signInView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        signInView = inflater.inflate(R.layout.fr_sign_in_screen, container, false)

        setupUI()
        setupViewModel()
        setGoogleAuth()
        observeGoogleAccount()

        return signInView
    }

    private fun setupUI() {
        etNameSignIn = signInView.findViewById(R.id.etNameSignIn)

        btnEnterNameSignIn = signInView.findViewById(R.id.btnEnterNameSignIn)
        btnEnterNameSignIn.setOnClickListener {
            (requireActivity() as MainContract).openHomeFragment()
        }
        btnGoogleSignIn = signInView.findViewById(R.id.btnGoogleSignIn)
        btnGoogleSignIn.setOnClickListener {
            requireActivity().startActivityForResult(signInClient.signInIntent, NEWS_SIGN_IN)
        }

        clFragmentLoginScreen = signInView.findViewById(R.id.clFragmentLoginScreen)
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
                    Snackbar.make(clFragmentLoginScreen, "error account", Snackbar.LENGTH_SHORT).show()
                }
            }
        })
    }
}