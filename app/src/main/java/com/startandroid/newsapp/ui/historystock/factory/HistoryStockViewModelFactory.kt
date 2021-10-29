package com.startandroid.newsapp.ui.historystock.factory

import android.net.ConnectivityManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.startandroid.newsapp.data.repository.NewsRepositoryImpl
import com.startandroid.newsapp.ui.historystock.viewmodel.HistoryStockViewModel

class HistoryStockViewModelFactory(private val connectivityManager: ConnectivityManager) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HistoryStockViewModel::class.java)) {
            return HistoryStockViewModel(NewsRepositoryImpl(connectivityManager)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}