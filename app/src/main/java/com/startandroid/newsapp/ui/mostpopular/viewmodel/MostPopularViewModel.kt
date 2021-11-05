package com.startandroid.newsapp.ui.mostpopular.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.startandroid.newsapp.data.model.PopularNews
import com.startandroid.newsapp.data.repository.repositorynews.NewsRepository
import com.startandroid.newsapp.utils.Result
import kotlinx.coroutines.launch

class MostPopularViewModel(private val repository: NewsRepository) : ViewModel() {

    private val mostPopularLiveData = MutableLiveData<Result<PopularNews>>()
    private val mostPopularLiveDataNet = MutableLiveData<String>()

    init {
        viewModelScope.launch {
            if (repository.isNetConnected()) {
                fetchMostPopular()
            } else {
                mostPopularLiveDataNet.postValue("Not net")
            }
        }
    }

    private suspend fun fetchMostPopular() {
        val mostPopularData = repository.getMostPopular()
        if (mostPopularData != null) {
            mostPopularLiveData.postValue(Result.successData(mostPopularData))
        } else {
            mostPopularLiveData.postValue(Result.errorData(null))
        }
    }

    fun getMostPopular(): LiveData<Result<PopularNews>> {
        Log.d("TAG_BACK", "getMostPopular | liveData: $mostPopularLiveData")
        return mostPopularLiveData
    }

    fun getMostPopularNet(): LiveData<String> {
        return mostPopularLiveDataNet
    }
}