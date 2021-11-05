package com.startandroid.newsapp.data.repository.repositoryflow

import kotlinx.coroutines.flow.Flow

interface UsingFlowRepository {

    suspend fun checkInternet(): Flow<Boolean>

    suspend fun checkLocation(): Flow<String>
}