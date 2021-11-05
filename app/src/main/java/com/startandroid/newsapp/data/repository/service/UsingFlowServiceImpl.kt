package com.startandroid.newsapp.data.repository.service

import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.Network
import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UsingFlowServiceImpl(
    private val connectivityManager: ConnectivityManager,
    private val locationManager: LocationManager
) : UsingFlowService {

    private var status = true
    private var location = ""

    @RequiresApi(Build.VERSION_CODES.N)
    override suspend fun checkInternet(): Flow<Boolean> = flow {
        connectivityManager.registerDefaultNetworkCallback(object :
            ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                setStatus(true)
            }

            override fun onLost(network: Network) {
                setStatus(false)
            }
        })
        emit(status)
    }

    private fun setStatus(status: Boolean) {
        this.status = status

    }

    override suspend fun checkLocation(): Flow<String> {
        TODO("Not yet implemented")
    }
}