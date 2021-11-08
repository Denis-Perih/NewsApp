package com.startandroid.newsapp.ui.usingflow.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.startandroid.newsapp.data.repository.repositoryflow.UsingFlowRepository
import com.startandroid.newsapp.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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
            repository.fetchInternetConnectionState()
        }
        observeInternetConnectionState()
        observeLocationState()
    }

    private fun observeInternetConnectionState() {
        repository.connectionStateFlow
            .onEach { _uiStateNet.value = Result.successData(it) }
            .launchIn(viewModelScope)
    }

    fun fetchLocationState() {
        repository.fetchLocationState()
    }

    private fun observeLocationState() {
        repository.locationStateFlow
            .onEach { _uiStateLocation.value = Result.successData(it) }
            .launchIn(viewModelScope)
    }
}