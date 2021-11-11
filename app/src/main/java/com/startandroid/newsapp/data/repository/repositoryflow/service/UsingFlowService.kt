package com.startandroid.newsapp.data.repository.repositoryflow.service

import kotlinx.coroutines.flow.Flow

interface UsingFlowService {

    val connectionStateFlow: Flow<Boolean>

    fun fetchInternetConnectionState()

    val locationStateFlow: Flow<String>

    fun fetchLocationState()
}