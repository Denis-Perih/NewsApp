package com.startandroid.newsapp.signin

import android.app.Activity
import android.content.Context
import android.content.Intent

interface SignInContract {

    interface View {

        fun startActivitySignIn(intent: Intent)

        fun showSnackBar(text: String)

        fun isSuccessAuthGoogle()
    }

    interface Presenter {

        fun setNameToPreference(name: String, context: Context)

        fun setGoogleAuth(activity: Activity)

        fun onLoginClickedGoogle()

        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    }
}