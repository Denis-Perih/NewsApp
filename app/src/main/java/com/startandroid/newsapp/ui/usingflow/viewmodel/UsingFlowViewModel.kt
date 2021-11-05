package com.startandroid.newsapp.ui.usingflow.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.startandroid.newsapp.data.repository.repositoryflow.UsingFlowRepository
import kotlinx.coroutines.launch
import com.startandroid.newsapp.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*

@InternalCoroutinesApi
class UsingFlowViewModel(private val repository: UsingFlowRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<Result<Boolean>> =
        MutableStateFlow(Result.successData(false))
    val state = _uiState.asSharedFlow()

    init {
        viewModelScope.launch {
            async(Dispatchers.IO) { fetchInternet() }
            async(Dispatchers.IO) { fetchLocation() }
        }
    }

    private suspend fun fetchInternet() {
        repository.checkInternet()
            .flowOn(Dispatchers.IO)
            .catch { _uiState.value = Result.errorData(true) }
            .collect { status -> _uiState.value = Result.successData(status)
                Log.d("TAGING", "fetchInternet: $status") }
    }

    private suspend fun fetchLocation() {

    }
}