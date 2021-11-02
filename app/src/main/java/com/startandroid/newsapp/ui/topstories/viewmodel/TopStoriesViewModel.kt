package com.startandroid.newsapp.ui.topstories.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.startandroid.newsapp.data.model.StoriesNews
import com.startandroid.newsapp.data.repository.NewsRepository
import com.startandroid.newsapp.utils.Result
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TopStoriesViewModel(private val repository: NewsRepository) : ViewModel() {

    private val topStoriesLiveData = MutableLiveData<Result<StoriesNews>>()
    private val topStoriesLiveDataNet = MutableLiveData<String>()
    private val compositeDisposable = CompositeDisposable()

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
        viewModelScope.launch(Dispatchers.IO) {
            val singleNews = repository.getTopStories()
            topStoriesLiveData.postValue(Result.successData(singleNews))
        }
    }

//    private fun fetchTopStories() {
//        compositeDisposable.add(
//            repository.getTopStories()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({ news ->
//                    topStoriesLiveData.postValue(Result.successData(news))
//                    Log.d("TAG_BACK", "fetchTopStories | liveData: $topStoriesLiveData")
//                }, { throwable ->
//                    topStoriesLiveData.postValue(Result.errorData(null))
//                })
//        )
//    }

    fun getTopStories(): LiveData<Result<StoriesNews>> {
        Log.d("TAG_BACK", "getTopStories | liveData: $topStoriesLiveData")
        return topStoriesLiveData
    }

    fun getTopStoriesNet(): LiveData<String> {
        return topStoriesLiveDataNet
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}