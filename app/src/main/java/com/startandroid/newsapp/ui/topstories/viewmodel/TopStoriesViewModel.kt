package com.startandroid.newsapp.ui.topstories.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.startandroid.newsapp.data.model.StoriesNews
import com.startandroid.newsapp.data.repository.NewsRepository
import com.startandroid.newsapp.utils.Result
import kotlinx.coroutines.launch

class TopStoriesViewModel(private val repository: NewsRepository) : ViewModel() {

    private val topStoriesLiveData = MutableLiveData<Result<StoriesNews>>()
    private val topStoriesLiveDataNet = MutableLiveData<String>()

    init {
        viewModelScope.launch() {
            if (repository.isNetConnected()) {
                fetchTopStories()
            } else {
                topStoriesLiveDataNet.postValue("Not net")
            }
        }
    }

    private suspend fun fetchTopStories() {
        val topStoriesData = repository.getTopStories()
        if (topStoriesData != null) {
            topStoriesLiveData.postValue(Result.successData(topStoriesData))
        } else {
            topStoriesLiveData.postValue(Result.errorData(null))
        }
    }

    fun getTopStories(): LiveData<Result<StoriesNews>> {
        Log.d("TAG_BACK", "getTopStories | liveData: $topStoriesLiveData")
        return topStoriesLiveData
    }

    fun getTopStoriesNet(): LiveData<String> {
        return topStoriesLiveDataNet
    }
}