package com.startandroid.newsapp.data.repository

import android.net.ConnectivityManager
import android.os.Build
import androidx.annotation.RequiresApi
import com.startandroid.newsapp.data.api.network.NetworkServiceHistory
import com.startandroid.newsapp.data.api.network.NetworkServiceTabOneTwo
import com.startandroid.newsapp.data.model.HistoryStock
import com.startandroid.newsapp.data.model.PopularNews
import com.startandroid.newsapp.data.model.StoriesNews
import io.reactivex.Single
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsRepositoryImpl(private val connectivityManager: ConnectivityManager) : NewsRepository {

    private val apiKey = "AX61V3XwAl1cpB4ZM04aSr5Ae7Ax4SGF"

    override suspend fun getMostPopular(): Single<PopularNews> {
        return withContext(Dispatchers.IO) {
            NetworkServiceTabOneTwo
                .getJSONApi()
                .getMostPopular(apiKey)
        }
    }

    override suspend fun getTopStories(): Single<StoriesNews> {
        return withContext(Dispatchers.IO) {
            NetworkServiceTabOneTwo
                .getJSONApi()
                .getTopStories(apiKey)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override suspend fun isNetConnected(): Boolean {
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                return true
            }
        }
        return false
    }

    override suspend fun getHistoryStock(start_date: String, end_date: String): Single<HistoryStock> {
        return withContext(Dispatchers.IO) {
            NetworkServiceHistory
                .getJSONApi().getHistoryStock(start_date, end_date)
        }
    }
}