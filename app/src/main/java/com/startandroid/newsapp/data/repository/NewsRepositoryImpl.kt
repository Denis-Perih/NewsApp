package com.startandroid.newsapp.data.repository

import android.net.ConnectivityManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.startandroid.newsapp.data.api.network.NetworkServiceHistory
import com.startandroid.newsapp.data.api.network.NetworkServiceTabOneTwo
import com.startandroid.newsapp.data.model.HistoryStock
import com.startandroid.newsapp.data.model.PopularNews
import com.startandroid.newsapp.data.model.StoriesNews
import com.startandroid.newsapp.utils.Result
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NewsRepositoryImpl(private val connectivityManager: ConnectivityManager) : NewsRepository {

    private val apiKey = "AX61V3XwAl1cpB4ZM04aSr5Ae7Ax4SGF"

    override fun getMostPopular(): Single<PopularNews> {

        return NetworkServiceTabOneTwo
            .getJSONApi()
            .getMostPopular(apiKey)
    }

    override fun getTopStories(): Single<StoriesNews> {
        return  NetworkServiceTabOneTwo
            .getJSONApi()
            .getTopStories(apiKey)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun isNetConnected(): Boolean {
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                return true
            }
        }
        return false
    }

    override fun getHistoryStock(start_date: String, end_date: String): Single<HistoryStock> {
        return NetworkServiceHistory
            .getJSONApi().getHistoryStock(start_date, end_date)
    }
}