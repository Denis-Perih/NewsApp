package com.startandroid.newsapp.ui.mostpopular.factory

import android.net.ConnectivityManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.startandroid.newsapp.data.repository.repositorymostpopular.MostPopularRepositoryImpl
import com.startandroid.newsapp.ui.mostpopular.viewmodel.MostPopularViewModel
import javax.inject.Inject

class MostPopularViewModelFactory @Inject constructor(private val mostPopularRepositoryImpl: MostPopularRepositoryImpl) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MostPopularViewModel::class.java)) {
            return MostPopularViewModel(mostPopularRepositoryImpl) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}