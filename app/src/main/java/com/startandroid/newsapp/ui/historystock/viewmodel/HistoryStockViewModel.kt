package com.startandroid.newsapp.ui.historystock.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.startandroid.newsapp.data.model.HistoryStockItem
import com.startandroid.newsapp.data.repository.NewsRepository
import com.startandroid.newsapp.utils.Result
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.Observable
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class HistoryStockViewModel(private val repository: NewsRepository) : ViewModel() {

    private val historyLiveData = MutableLiveData<Result<HistoryStockItem>>()
    private val compositeDisposable = CompositeDisposable()

    private val calendar = GregorianCalendar(1980, Calendar.DECEMBER, 12)

    init {
        goRequestInterval()
    }

    @SuppressLint("SimpleDateFormat")
    private fun goRequestInterval() {
        compositeDisposable.add(
            Observable.interval(1000L, TimeUnit.MILLISECONDS)
                .timeInterval()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    val dateFormat = SimpleDateFormat("yyyy-MM-dd")

                    if (dateFormat.format(calendar.time) == "2018-03-28" ) {
                        calendar.set(1980, Calendar.DECEMBER, 12)
                        fetchHistory(dateFormat.format(calendar.time))
                        calendar.add(Calendar.DAY_OF_YEAR, 1)
                    } else {
                        fetchHistory(dateFormat.format(calendar.time))
                        calendar.add(Calendar.DAY_OF_YEAR, 1)
                    }
                }
        )
    }

    private fun fetchHistory(date: String) {
        compositeDisposable.add(
            repository.getHistoryStock(date, date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ stories ->
                    historyLiveData.postValue(Result.successData(stories.dataset_data))
                }, { throwable ->
                    historyLiveData.postValue(Result.errorData(null))
                })
        )
    }

    override fun onCleared() {
        compositeDisposable.dispose()
    }

    fun getHistory(): LiveData<Result<HistoryStockItem>> {
        return historyLiveData
    }
}