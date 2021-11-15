package com.startandroid.newsapp.ui.usingflow.view

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.startandroid.newsapp.R
import com.startandroid.newsapp.data.application.NewsApplication
import com.startandroid.newsapp.databinding.FrUsingFlowBinding
import com.startandroid.newsapp.ui.usingflow.factory.UsingFlowViewModelFactory
import com.startandroid.newsapp.ui.usingflow.viewmodel.UsingFlowViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@InternalCoroutinesApi
class UsingFlowFragment : Fragment(R.layout.fr_using_flow) {

    private var bind: FrUsingFlowBinding? = null
    private val binding get() = bind!!

    @Inject
    lateinit var usingFlowViewModelFactory: UsingFlowViewModelFactory

    private val usingFlowViewModel: UsingFlowViewModel by viewModels { usingFlowViewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        NewsApplication.INSTANCE.appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind = FrUsingFlowBinding.bind(view)

        setupFlow()
        setupLocation()
    }

    private fun setupFlow() {
        usingFlowViewModel.stateLocation
            .onEach { binding.tvLocationValue.text = it.data }
            .launchIn(lifecycleScope)

        usingFlowViewModel.stateNet
            .onEach {
                when (it.data == true) {
                    true -> binding.tvNetValue.setText(R.string.connected)
                    false -> binding.tvNetValue.setText(R.string.no_connected)
                } }
            .launchIn(lifecycleScope)
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