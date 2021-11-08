package com.startandroid.newsapp.data.repository.repositoryflow

import com.startandroid.newsapp.data.repository.service.UsingFlowService
import kotlinx.coroutines.flow.Flow

class UsingFlowRepositoryImpl(private val service: UsingFlowService) : UsingFlowRepository {

    override val connectionStateFlow: Flow<Boolean> = service.connectionStateFlow

    override fun fetchInternetConnectionState() =
        service.fetchInternetConnectionState()

    override val locationStateFlow: Flow<String> = service.locationStateFlow

    override fun fetchLocationState() =
        service.fetchLocationState()

}