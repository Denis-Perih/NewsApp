package com.startandroid.newsapp.data.repository.repositoryhistorystock

import com.startandroid.newsapp.data.api.network.ApiRequest
import com.startandroid.newsapp.data.model.HistoryStock
import com.startandroid.newsapp.utils.qualifier.HistoryStockApiRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HistoryStockRepositoryImpl @Inject constructor(@HistoryStockApiRequest private val historyApiRequest: ApiRequest): HistoryStockRepository {

    override suspend fun getHistoryStock(date: String): HistoryStock {
        return withContext(Dispatchers.IO) {
            historyApiRequest.getHistoryStock(date, date)
        }
    }
}