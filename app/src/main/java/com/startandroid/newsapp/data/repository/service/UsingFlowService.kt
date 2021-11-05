package com.startandroid.newsapp.data.repository.service

import kotlinx.coroutines.flow.Flow

interface UsingFlowService {

    suspend fun checkInternet(): Flow<Boolean>

    suspend fun checkLocation(): Flow<String>
}