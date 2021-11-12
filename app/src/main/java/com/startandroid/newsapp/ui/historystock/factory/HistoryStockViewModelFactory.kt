package com.startandroid.newsapp.ui.historystock.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.startandroid.newsapp.data.repository.repositoryhistorystock.HistoryStockRepositoryImpl
import com.startandroid.newsapp.ui.historystock.viewmodel.HistoryStockViewModel
import kotlinx.coroutines.ObsoleteCoroutinesApi
import javax.inject.Inject

class HistoryStockViewModelFactory @Inject constructor(
    private val historiStockRepositoryImpl: HistoryStockRepositoryImpl
) : ViewModelProvider.Factory {

    @ObsoleteCoroutinesApi
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HistoryStockViewModel::class.java)) {
            return HistoryStockViewModel(historiStockRepositoryImpl) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}