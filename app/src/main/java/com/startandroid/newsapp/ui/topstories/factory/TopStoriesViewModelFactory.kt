package com.startandroid.newsapp.ui.topstories.factory

import android.net.ConnectivityManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.startandroid.newsapp.data.repository.repositorytopstories.TopStoriesRepositoryImpl
import com.startandroid.newsapp.ui.topstories.viewmodel.TopStoriesViewModel
import javax.inject.Inject

class TopStoriesViewModelFactory @Inject constructor(
    private val topStoriesRepositoryImpl: TopStoriesRepositoryImpl
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TopStoriesViewModel::class.java)) {
            return TopStoriesViewModel(topStoriesRepositoryImpl) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}