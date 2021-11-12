package com.startandroid.newsapp.data.repository.repositoryhistorystock

import com.startandroid.newsapp.data.model.HistoryStock

interface HistoryStockRepository {

    suspend fun getHistoryStock(date: String): HistoryStock
}