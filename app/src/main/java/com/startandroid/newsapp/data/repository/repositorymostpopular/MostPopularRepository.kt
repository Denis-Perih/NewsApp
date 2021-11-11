package com.startandroid.newsapp.data.repository.repositorymostpopular

import com.startandroid.newsapp.data.model.PopularNews

interface MostPopularRepository {

    suspend fun getMostPopular(): PopularNews
}