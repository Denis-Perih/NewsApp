package com.startandroid.newsapp.ui.topstories.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.startandroid.newsapp.data.model.StoriesNews
import com.startandroid.newsapp.data.repository.NewsRepository
import com.startandroid.newsapp.utils.Result
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class TopStoriesViewModel(private val repository: NewsRepository) : ViewModel() {

    private val topStoriesLiveData = MutableLiveData<Result<StoriesNews>>()
    private val topStoriesLiveDataNet = MutableLiveData<String>()
    private val compositeDisposable = CompositeDisposable()

    init {
        if (repository.isNetConnected()) {
            fetchTopStories()
        } else {
            topStoriesLiveDataNet.postValue("Not net")
        }
    }

    private fun fetchTopStories() {
        compositeDisposable.add(
            repository.getTopStories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ news ->
                    topStoriesLiveData.postValue(Result.successData(news))
                    Log.d("TAG_BACK", "fetchTopStories | liveData: $topStoriesLiveData")
                }, { throwable ->
                    topStoriesLiveData.postValue(Result.errorData(null))
                })
        )
    }

    fun getTopStories(): LiveData<Result<StoriesNews>> {
        Log.d("TAG_BACK", "getTopStories | liveData: $topStoriesLiveData")
        return topStoriesLiveData
    }

    fun getTopStoriesNet() : LiveData<String> {
        return topStoriesLiveDataNet
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}