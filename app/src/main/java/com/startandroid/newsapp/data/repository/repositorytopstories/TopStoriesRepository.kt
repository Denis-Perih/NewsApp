package com.startandroid.newsapp.data.repository.repositorytopstories

import com.startandroid.newsapp.data.model.StoriesNews

interface TopStoriesRepository {
    suspend fun getTopStories(): StoriesNews
}