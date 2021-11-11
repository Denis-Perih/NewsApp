package com.startandroid.newsapp.data.repository.repositoryflow

import android.os.Build
import androidx.annotation.RequiresApi
import com.startandroid.newsapp.data.repository.repositoryflow.service.UsingFlowService
import com.startandroid.newsapp.data.repository.repositoryflow.service.UsingFlowServiceImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UsingFlowRepositoryImpl @Inject constructor(private val service: UsingFlowServiceImpl) : UsingFlowRepository {

    override val connectionStateFlow: Flow<Boolean> = service.connectionStateFlow

    @RequiresApi(Build.VERSION_CODES.N)
    override fun fetchInternetConnectionState() =
        service.fetchInternetConnectionState()

    override val locationStateFlow: Flow<String> = service.locationStateFlow

    override fun fetchLocationState() =
        service.fetchLocationState()

}