package com.startandroid.newsapp.data.repository.repositorytopstories

import com.startandroid.newsapp.data.api.network.ApiRequest
import com.startandroid.newsapp.data.model.StoriesNews
import com.startandroid.newsapp.utils.qualifier.NewsApiRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class TopStoriesRepositoryImpl @Inject constructor(@NewsApiRequest private val topStoriesApiRequest: ApiRequest): TopStoriesRepository {

    private val apiKey = "AX61V3XwAl1cpB4ZM04aSr5Ae7Ax4SGF"

    override suspend fun getTopStories(): StoriesNews {
        return withContext(Dispatchers.IO) {
            topStoriesApiRequest.getTopStories(apiKey)
        }
    }
}