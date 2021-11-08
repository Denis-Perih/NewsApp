package com.startandroid.newsapp.ui.usingflow.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.startandroid.newsapp.data.repository.repositoryflow.UsingFlowRepository
import com.startandroid.newsapp.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@InternalCoroutinesApi
class UsingFlowViewModel(private val repository: UsingFlowRepository) : ViewModel() {

    private val _uiStateNet: MutableStateFlow<Result<Boolean>> =
        MutableStateFlow(Result.successData(false))
    val stateNet = _uiStateNet.asSharedFlow()

    private val _uiStateLocation: MutableStateFlow<Result<String>> =
        MutableStateFlow(Result.successData("Location"))
    val stateLocation = _uiStateLocation.asSharedFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            async { repository.fetchInternetConnectionState()
                observeInternetConnectionState() }
            async { observeLocationState() }
        }
    }

    private suspend fun observeInternetConnectionState() {
        repository.connectionStateFlow
            .collect { isConnected ->
                _uiStateNet.value = Result.successData(isConnected) }
    }

    fun fetchLocationState() {
        repository.fetchLocationState()
    }

    private suspend fun observeLocationState() {
        repository.locationStateFlow
            .collect { location ->
                _uiStateLocation.value = Result.successData(location)
            }
    }
}