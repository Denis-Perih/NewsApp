package com.startandroid.newsapp.ui.usingflow.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.startandroid.newsapp.data.repository.repositoryflow.UsingFlowRepositoryImpl
import com.startandroid.newsapp.ui.usingflow.viewmodel.UsingFlowViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject

@InternalCoroutinesApi
class UsingFlowViewModelFactory @Inject constructor(
    private val usingFlowRepositoryImpl: UsingFlowRepositoryImpl
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UsingFlowViewModel::class.java)) {
            return UsingFlowViewModel(usingFlowRepositoryImpl) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}