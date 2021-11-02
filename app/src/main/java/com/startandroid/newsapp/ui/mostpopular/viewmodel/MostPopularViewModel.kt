package com.startandroid.newsapp.ui.mostpopular.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.startandroid.newsapp.data.model.PopularNews
import com.startandroid.newsapp.data.repository.NewsRepository
import com.startandroid.newsapp.utils.Result
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MostPopularViewModel(private val repository: NewsRepository) : ViewModel() {

    private val mostPopularLiveData = MutableLiveData<Result<PopularNews>>()
    private val mostPopularLiveDataNet = MutableLiveData<String>()
    private val compositeDisposable = CompositeDisposable()

    init {
        if (repository.isNetConnected()) {
            fetchMostPopular()
        } else {
            mostPopularLiveDataNet.postValue("Not net")
        }
    }

    private fun fetchMostPopular() {
        compositeDisposable.add(
            repository.getMostPopular()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ news ->
                    mostPopularLiveData.postValue(Result.successData(news))
                    Log.d("TAG_BACK", "fetchMostPopular | liveData: $mostPopularLiveData")
                }, { throwable ->
                    mostPopularLiveData.postValue(Result.errorData(null))
                })
        )
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