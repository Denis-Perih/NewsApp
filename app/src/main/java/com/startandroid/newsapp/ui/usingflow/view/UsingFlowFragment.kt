package com.startandroid.newsapp.ui.usingflow.view

import android.Manifest
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.LocationManager
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.startandroid.newsapp.R
import com.startandroid.newsapp.databinding.FrUsingFlowBinding
import com.startandroid.newsapp.ui.usingflow.factory.UsingFlowViewModelFactory
import com.startandroid.newsapp.ui.usingflow.viewmodel.UsingFlowViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@InternalCoroutinesApi
class UsingFlowFragment() : Fragment(R.layout.fr_using_flow) {

    private var locationManager: LocationManager? = null

    private var bind: FrUsingFlowBinding? = null
    private val binding get() = bind!!

    private lateinit var usingFlowViewModel: UsingFlowViewModel
    private lateinit var connectivityManager: ConnectivityManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind = FrUsingFlowBinding.bind(view)

        setupUI()
        setupViewModel()
        setupFlow()
        setupLocation()
    }

    private fun setupUI() {
        connectivityManager = requireActivity().applicationContext
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        locationManager = requireActivity().getSystemService(LOCATION_SERVICE) as LocationManager?
    }

    private fun setupViewModel() {
        usingFlowViewModel = ViewModelProviders.of(
            this,
            UsingFlowViewModelFactory(connectivityManager, locationManager)
        )[UsingFlowViewModel::class.java]
    }

    private fun setupFlow() {
        lifecycleScope.launch {
            async { usingFlowViewModel.stateLocation.collect { state ->
                binding.tvLocationValue.text = state.data
            } }
            async { usingFlowViewModel.stateNet.collect { state ->
                if (state.data == true) {
                    binding.tvNetValue.setText(R.string.connected)
                } else {
                    binding.tvNetValue.setText(R.string.no_connected)
                }
            } }
        }
    }

    private fun setupLocation() {
        if (checkLocationPermission()) {
            usingFlowViewModel.fetchLocationState()
        } else
            requestLocationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)

    }

    private val requestLocationPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                usingFlowViewModel.fetchLocationState()
            } else {
                binding.tvLocationValue.setText(R.string.location_value)
            }
        }

    private fun checkLocationPermission(): Boolean {
        val permission = Manifest.permission.ACCESS_FINE_LOCATION
        val res = requireContext().checkCallingOrSelfPermission(permission)
        return res == PackageManager.PERMISSION_GRANTED
    }

    override fun onDestroyView() {
        bind = null
        super.onDestroyView()
    }
}