package com.startandroid.newsapp.data.repository.repositoryflow.service

import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.Network
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class UsingFlowServiceImpl @Inject constructor(
    private val connectivityManager: ConnectivityManager,
    private val locationManager: LocationManager?
) : UsingFlowService {

    private val _connectionStateFlow = MutableStateFlow(false)
    override val connectionStateFlow: Flow<Boolean> = _connectionStateFlow.asStateFlow()

    private val _locationStateFlow = MutableStateFlow("")
    override val locationStateFlow: Flow<String> = _locationStateFlow.asStateFlow()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun fetchInternetConnectionState() {
        connectivityManager.registerDefaultNetworkCallback(object :
            ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                _connectionStateFlow.tryEmit(true)
                Log.d("Tagger", "onAvailable: connected")
            }

            override fun onLost(network: Network) {
                _connectionStateFlow.tryEmit(false)
                Log.d("Tagger", "onLost: no connected")
            }
        })
    }

    override fun fetchLocationState() {
        try {
            // Request location updates
            locationManager?.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000L, 0f, locationListener)
        } catch(ex: SecurityException) {
            Log.d("myTag", "Security Exception, no location available")
        }
    }

    private val locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            val address = String.format("lat = %1$.4f, lon = %2$.4f",
                location.latitude, location.longitude)
            _locationStateFlow.tryEmit(address)
            Log.d("Tagger", "onLocationChanged: $address")
        }
        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
            super.onStatusChanged(provider, status, extras)
        }
    }
}