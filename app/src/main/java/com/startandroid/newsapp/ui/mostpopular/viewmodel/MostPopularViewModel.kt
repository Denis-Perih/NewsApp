package com.startandroid.newsapp.ui.mostpopular.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.startandroid.newsapp.data.model.PopularNews
import com.startandroid.newsapp.data.model.PopularNewsItem
import com.startandroid.newsapp.data.repository.NewsRepository
import com.startandroid.newsapp.ui.mostpopular.ItemForMostPopular
import com.startandroid.newsapp.utils.Result
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MostPopularViewModel(private val repository: NewsRepository) : ViewModel() {

    private val mostPopularLiveData = MutableLiveData<Result<PopularNews>>()
    private val mostPopularLiveDataNet = MutableLiveData<String>()
    private val mostPopularLiveDataItemNews = MutableLiveData<PopularNewsItem>()
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
                }, { throwable ->
                    mostPopularLiveData.postValue(Result.errorData(null))
                })
        )
    }

    fun getMostPopular(): LiveData<Result<PopularNews>> {
        return mostPopularLiveData
    }

    fun getMostPopularNet() : LiveData<String> {
        return mostPopularLiveDataNet
    }

    fun getMostPopularItemMore() : LiveData<PopularNewsItem> {
        return mostPopularLiveDataItemNews
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}