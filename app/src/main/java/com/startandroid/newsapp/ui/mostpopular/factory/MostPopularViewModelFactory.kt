package com.startandroid.newsapp.ui.mostpopular.factory

import android.net.ConnectivityManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.startandroid.newsapp.data.repository.NewsRepositoryImpl
import com.startandroid.newsapp.ui.mostpopular.viewmodel.MostPopularViewModel

class MostPopularViewModelFactory(private val connectivityManager: ConnectivityManager) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MostPopularViewModel::class.java)) {
            return MostPopularViewModel(NewsRepositoryImpl(connectivityManager)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}