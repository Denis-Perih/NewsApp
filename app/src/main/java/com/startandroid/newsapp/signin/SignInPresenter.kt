package com.startandroid.newsapp.signin

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.startandroid.newsapp.data.NewsRepositoryImpl

/*
Client ID
    226954801878-hpu0ps56rolgm8n56p00e540d6r2kk0t.apps.googleusercontent.com

Client Secret
    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
*/

class SignInPresenter(private val view: SignInContract.View) : SignInContract.Presenter {

    lateinit var preferences: SharedPreferences
    lateinit var signInClient: GoogleSignInClient
    val newsRepository = NewsRepositoryImpl()

    companion object {
        const val PREFERENCES_KEY = "news_name"
        const val NAME_KEY = "user_name"
        const val NEWS_SIGN_IN = 0
    }

    override fun setNameToPreference(name: String, context: Context) {
        preferences = context.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = preferences.edit()
        editor.putString(NAME_KEY, name)
        editor.apply()
    }

    override fun setGoogleAuth(activity: Activity) {
        val gso: GoogleSignInOptions =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

        signInClient = GoogleSignIn.getClient(activity, gso)
    }

    override fun onLoginClickedGoogle() {
        view.startActivitySignIn(signInClient.signInIntent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == NEWS_SIGN_IN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                view.isSuccessAuthGoogle()
            } catch (e: ApiException) {
                view.showSnackBar(e.toString())
            }
        }
    }
}