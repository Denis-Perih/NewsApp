package com.startandroid.newsapp.ui.historystock.viewmodel

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
import java.util.*
import java.util.concurrent.TimeUnit

class HistoryStockViewModel(private val repository: NewsRepository) : ViewModel() {

    private val historyLiveData = MutableLiveData<Result<HistoryStockItem>>()
    private val compositeDisposable = CompositeDisposable()

//    private lateinit var countDownTimer: CountDownTimer

//    private var year = 1980
//    private var month = 12
//    private var day = 28

    init {
        goRequestInterval()
    }

    private fun goRequestInterval() {
        compositeDisposable.add(
            Observable.interval(1000L, TimeUnit.MILLISECONDS)
                .timeInterval()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { Log.d("tag", "&&&& on timer") }
        )
    }

//    private fun goRequestTimer() {
//
//        countDownTimer = object : CountDownTimer(1000, 1000) {
//            override fun onTick(millisUntilFinished: Long) {
//            }
//
//            override fun onFinish() {
//                Log.d("TIME_LOG", "Done!")
//
//                val date = "$year-$month-$day"
//
//                if (day <= 31) {
//                    day += 1
//                } else {
//                    day = 1
//                    if (month <=12) {
//                        month += 1
//                    } else {
//                        month = 1
//                        if (year <= 2018) {
//                            year += 1
//                        } else {
//                            year = 1980
//                        }
//                    }
//                }
//
//                fetchHistory(date)
//
//                updateCounter()
//            }
//        }.start()
//    }
//
//    private fun updateCounter() {
//        countDownTimer.cancel()
//        goRequestTimer()
//    }

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