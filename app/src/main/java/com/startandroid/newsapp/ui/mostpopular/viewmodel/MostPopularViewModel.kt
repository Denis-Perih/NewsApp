package com.startandroid.newsapp.ui.mostpopular.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.startandroid.newsapp.data.model.PopularNews
import com.startandroid.newsapp.data.repository.NewsRepository
import com.startandroid.newsapp.utils.Result
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MostPopularViewModel(private val repository: NewsRepository) : ViewModel() {

    private val mostPopularLiveData = MutableLiveData<Result<PopularNews>>()
    private val mostPopularLiveDataNet = MutableLiveData<String>()
    private val compositeDisposable = CompositeDisposable()

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
            val singleNews = repository.getMostPopular()
            if (singleNews != null) {
                mostPopularLiveData.postValue(Result.successData(singleNews))
                Log.d("TAG", "fetchMostPopular2: ${singleNews}")
            } else {
                mostPopularLiveData.postValue(Result.errorData(null))
            }
    }

    fun getMostPopular(): LiveData<Result<PopularNews>> {
        Log.d("TAG_BACK", "getMostPopular | liveData: $mostPopularLiveData")
        return mostPopularLiveData
    }

    fun getMostPopularNet() : LiveData<String> {
        return mostPopularLiveDataNet
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}