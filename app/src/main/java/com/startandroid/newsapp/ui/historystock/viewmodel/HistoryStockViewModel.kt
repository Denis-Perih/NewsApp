package com.startandroid.newsapp.ui.historystock.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.startandroid.newsapp.data.model.HistoryStockItem
import com.startandroid.newsapp.data.repository.repositorynews.NewsRepository
import com.startandroid.newsapp.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class HistoryStockViewModel(private val repository: NewsRepository) : ViewModel() {

    private val historyLiveData = MutableLiveData<Result<HistoryStockItem>>()
    private val historyLiveDataNet = MutableLiveData<String>()

    private val calendar = GregorianCalendar(1980, Calendar.DECEMBER, 12)

    private val tickerChannel = ticker(1000L)

    init {
        viewModelScope.launch {
            if (repository.isNetConnected()) {
                goRequestTicker()
            } else {
                historyLiveDataNet.postValue("Not net")
            }
        }
    }

    @ObsoleteCoroutinesApi
    @SuppressLint("SimpleDateFormat")
    private suspend fun goRequestTicker() {
        for (event in tickerChannel) {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd")
            if (dateFormat.format(calendar.time) == "2018-03-28") {
                calendar.set(1980, Calendar.DECEMBER, 12)
                fetchHistory(dateFormat.format(calendar.time))
                calendar.add(Calendar.DAY_OF_YEAR, 1)
            } else {
                fetchHistory(dateFormat.format(calendar.time))
                calendar.add(Calendar.DAY_OF_YEAR, 1)
            }
        }
    }

    private suspend fun fetchHistory(date: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val historyStockData = repository.getHistoryStock(date, date)
            if (historyStockData != null) {
                historyLiveData.postValue(Result.successData(historyStockData.dataset_data))
            } else {
                historyLiveData.postValue(Result.errorData(null))
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        tickerChannel.cancel()
    }

    fun getHistoryStockNet() : LiveData<String> {
        return historyLiveDataNet
    }

    fun getHistory(): LiveData<Result<HistoryStockItem>> {
        return historyLiveData
    }
}