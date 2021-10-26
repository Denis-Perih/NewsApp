package com.startandroid.newsapp.data.repository

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import androidx.annotation.RequiresApi
import com.startandroid.newsapp.data.api.network.NetworkServiceTabOneTwo
import com.startandroid.newsapp.data.model.PopularNews
import com.startandroid.newsapp.data.model.StoriesNews
import io.reactivex.Single

class NewsRepositoryImpl(private val connectivityManager: ConnectivityManager) : NewsRepository {

    private val apiKey = "AX61V3XwAl1cpB4ZM04aSr5Ae7Ax4SGF"

    override fun getMostPopular(): Single<PopularNews> {

        return NetworkServiceTabOneTwo
            .getJSONApi()
            .getNews(apiKey)
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
}