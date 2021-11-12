package com.startandroid.newsapp.data.repository.repositorymostpopular

import com.startandroid.newsapp.data.api.network.ApiRequest
import com.startandroid.newsapp.data.model.PopularNews
import com.startandroid.newsapp.utils.qualifier.NewsApiRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class MostPopularRepositoryImpl @Inject constructor(@NewsApiRequest private val mostPopularApiRequest: ApiRequest): MostPopularRepository {

    private val apiKey = "AX61V3XwAl1cpB4ZM04aSr5Ae7Ax4SGF"

    override suspend fun getMostPopular(): PopularNews {
        return withContext(Dispatchers.IO) {
            mostPopularApiRequest.getMostPopular(apiKey)
        }
    }
}