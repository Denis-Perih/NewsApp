package com.startandroid.newsapp.ui.topstories.factory

import android.net.ConnectivityManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.startandroid.newsapp.data.repository.NewsRepositoryImpl
import com.startandroid.newsapp.ui.mostpopular.viewmodel.MostPopularViewModel
import com.startandroid.newsapp.ui.topstories.viewmodel.TopStoriesViewModel

class TopStoriesViewModelFactory(private val connectivityManager: ConnectivityManager) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TopStoriesViewModel::class.java)) {
            return TopStoriesViewModel(NewsRepositoryImpl(connectivityManager)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}