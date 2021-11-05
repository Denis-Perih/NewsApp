package com.startandroid.newsapp.ui.usingflow.factory

import android.location.LocationManager
import android.net.ConnectivityManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.startandroid.newsapp.data.repository.repositoryflow.UsingFlowRepositoryImpl
import com.startandroid.newsapp.data.repository.service.UsingFlowServiceImpl
import com.startandroid.newsapp.ui.usingflow.viewmodel.UsingFlowViewModel
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class UsingFlowViewModelFactory(
    private val connectivityManager: ConnectivityManager,
    private val locationManager: LocationManager
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UsingFlowViewModel::class.java)) {
            return UsingFlowViewModel(
                UsingFlowRepositoryImpl(
                    UsingFlowServiceImpl(
                        connectivityManager, locationManager
                    )
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}