package com.startandroid.newsapp.ui.signin.viewmodel

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.startandroid.newsapp.utils.Result

/*
Client ID
    226954801878-hpu0ps56rolgm8n56p00e540d6r2kk0t.apps.googleusercontent.com

Client Secret
    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
*/

class SignInViewModel() : ViewModel() {

    private val accountLiveData = MutableLiveData<Result<GoogleSignInAccount>>()
    private val gsoLiveData = MutableLiveData<Result<GoogleSignInOptions>>()
    private lateinit var gso: GoogleSignInOptions

    companion object {
        const val NEWS_SIGN_IN = 0
    }

    init {
        fetchGoogle()
    }

    private fun fetchGoogle() {
        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
        gsoLiveData.postValue(Result.successData(gso))
    }

    fun getGSO(): LiveData<Result<GoogleSignInOptions>> {
        return gsoLiveData
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        if (requestCode == NEWS_SIGN_IN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account: GoogleSignInAccount = task.getResult(ApiException::class.java)
                accountLiveData.postValue(Result.successData(account))
            } catch (e: ApiException) {
                accountLiveData.postValue(Result.errorData(null))
            }
        }
    }

    fun getGoogleAccount() : LiveData<Result<GoogleSignInAccount>> {
        return accountLiveData
    }
}