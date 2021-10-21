package com.startandroid.newsapp.signin

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.startandroid.newsapp.R
import com.startandroid.newsapp.main.MainContract

class SignInFragment : Fragment(), SignInContract.View {

    private val presenter: SignInContract.Presenter = SignInPresenter(this)

    private lateinit var clFragmentLoginScreen: ConstraintLayout
    private lateinit var etNameSignIn: EditText
    private lateinit var btnEnterNameSignIn: Button
    private lateinit var btnGoogleSignIn: Button

    companion object {
        const val NEWS_SIGN_IN = 0
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val signInView: View = inflater.inflate(R.layout.fr_sign_in_screen, container, false)

        presenter.setGoogleAuth(requireActivity())

        etNameSignIn = signInView.findViewById(R.id.etNameSignIn)

        btnEnterNameSignIn = signInView.findViewById(R.id.btnEnterNameSignIn)
        btnEnterNameSignIn.setOnClickListener {
            presenter.setNameToPreference(etNameSignIn.text.toString(), requireContext())
            (requireActivity() as MainContract).openHomeFragment()
        }
        btnGoogleSignIn = signInView.findViewById(R.id.btnGoogleSignIn)
        btnGoogleSignIn.setOnClickListener { presenter.onLoginClickedGoogle() }

        clFragmentLoginScreen = signInView.findViewById(R.id.clFragmentLoginScreen)

        return signInView
    }

    override fun startActivitySignIn(intent: Intent) {
        requireActivity().startActivityForResult(intent, NEWS_SIGN_IN)
    }

    override fun showSnackBar(text: String) {
        Snackbar.make(clFragmentLoginScreen, text, Snackbar.LENGTH_SHORT).show()
    }

    override fun isSuccessAuthGoogle() {
        (requireActivity() as MainContract).openHomeFragment()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        presenter.onActivityResult(requestCode, resultCode, data)
    }
}