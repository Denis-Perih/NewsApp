package com.startandroid.newsapp.ui.topstories.viewmodel

import android.accounts.NetworkErrorException
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.startandroid.newsapp.data.model.StoriesNews
import com.startandroid.newsapp.data.repository.repositorymostpopular.MostPopularRepository
import com.startandroid.newsapp.data.repository.repositorytopstories.TopStoriesRepository
import com.startandroid.newsapp.utils.Result
import kotlinx.coroutines.launch
import javax.inject.Inject

class TopStoriesViewModel (private val repository: TopStoriesRepository) : ViewModel() {

    private val topStoriesLiveData = MutableLiveData<Result<StoriesNews>>()

    init {
        viewModelScope.launch() {
            fetchTopStories()
        }
    }

    private suspend fun fetchTopStories() {
        try {
            val topStoriesData = repository.getTopStories()
            topStoriesLiveData.postValue(Result.successData(topStoriesData))
        }catch (ex: NetworkErrorException){
            topStoriesLiveData.postValue(Result.errorData(null))
        }
    }

    fun getTopStories(): LiveData<Result<StoriesNews>> {
        Log.d("TAG_BACK", "getTopStories | liveData: $topStoriesLiveData")
        return topStoriesLiveData
    }
}