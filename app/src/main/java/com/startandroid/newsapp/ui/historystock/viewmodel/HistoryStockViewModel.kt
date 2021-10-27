package com.startandroid.newsapp.ui.historystock.viewmodel

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.startandroid.newsapp.data.model.HistoryStock
import com.startandroid.newsapp.data.model.HistoryStockItem
import com.startandroid.newsapp.data.repository.NewsRepository
import com.startandroid.newsapp.utils.Result
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*

class HistoryStockViewModel(private val repository: NewsRepository) : ViewModel() {

    private val historyLiveData = MutableLiveData<Result<HistoryStockItem>>()
    private val compositeDisposable = CompositeDisposable()

//    private lateinit var countDownTimer: CountDownTimer
//    private var counter: Long = 2000

    init {
        fetchHistory()
    }

//    private fun goRequestTimer() {
//
//        countDownTimer = object : CountDownTimer(counter, 1000) {
//            override fun onTick(millisUntilFinished: Long) {
//                Log.d("TIME_LOG", "Tick!")
//            }
//
//            override fun onFinish() {
//                Log.d("TIME_LOG", "Done!")
//            }
//        }
//    }

//    fun printDifferenceDateForHours() {
//
//        val currentTime = Calendar.getInstance().time
//        val endDateDay = "03/02/2020 21:00:00"
//        val format1 = SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.getDefault())
//        val endDate = format1.parse(endDateDay)
//
//        //milliseconds
//        var different = endDate.time - currentTime.time
//        countDownTimer = object : CountDownTimer(counter, 1000) {
//
//            override fun onTick(millisUntilFinished: Long) {
//                var diff = millisUntilFinished
//                val secondsInMilli: Long = 1000
//                val minutesInMilli = secondsInMilli * 60
//                val hoursInMilli = minutesInMilli * 60
//                val daysInMilli = hoursInMilli * 24
//
//                val elapsedDays = diff / daysInMilli
//                diff %= daysInMilli
//
//                val elapsedHours = diff / hoursInMilli
//                diff %= hoursInMilli
//
//                val elapsedMinutes = diff / minutesInMilli
//                diff %= minutesInMilli
//
//                val elapsedSeconds = diff / secondsInMilli
//
//                Log.d("TIME_LOG", "Time: " + "$elapsedDays days $elapsedHours hs $elapsedMinutes min $elapsedSeconds sec")
//            }
//
//            override fun onFinish() {
//
//            }
//        }.start()
//    }

    private fun fetchHistory() {
        compositeDisposable.add(
            repository.getHistoryStock("2018-03-27", "2018-03-27")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ stories ->
                    historyLiveData.postValue(Result.successData(stories.dataset_data.data))
//                    Log.d("TAG_DATA", "fet.setupObserver: " + stories.data)
                }, { throwable ->
                    historyLiveData.postValue(Result.errorData(null))
//                    Log.d("TAG_DATA", "setupObserver: " + null)
                })
        )
    }

    override fun onCleared() {
        compositeDisposable.dispose()
//        countDownTimer.cancel()
    }

    fun getHistory(): LiveData<Result<HistoryStockItem>> {
        return historyLiveData
    }
}