package com.startandroid.newsapp.data.repository.repositoryflow

import kotlinx.coroutines.flow.Flow

interface UsingFlowRepository {

    val connectionStateFlow: Flow<Boolean>

    fun fetchInternetConnectionState()

    val locationStateFlow: Flow<String>

    fun fetchLocationState()
}