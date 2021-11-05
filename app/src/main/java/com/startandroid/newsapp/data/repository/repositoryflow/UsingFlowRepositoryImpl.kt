package com.startandroid.newsapp.data.repository.repositoryflow

import com.startandroid.newsapp.data.repository.service.UsingFlowService
import kotlinx.coroutines.flow.Flow

class UsingFlowRepositoryImpl(private val service: UsingFlowService): UsingFlowRepository {

    override suspend fun checkInternet(): Flow<Boolean> {
        return service.checkInternet()
    }

    override suspend fun checkLocation(): Flow<String> {
        TODO("Not yet implemented")
    }

}