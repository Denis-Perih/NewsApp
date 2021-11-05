package com.startandroid.newsapp.ui.usingflow.view

import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.location.LocationManager
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.startandroid.newsapp.R
import com.startandroid.newsapp.databinding.FrUsingFlowBinding
import com.startandroid.newsapp.ui.usingflow.factory.UsingFlowViewModelFactory
import com.startandroid.newsapp.ui.usingflow.viewmodel.UsingFlowViewModel
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class UsingFlowFragment() : Fragment(R.layout.fr_using_flow) {

    private var bind: FrUsingFlowBinding? = null
    private val binding get() = bind!!

    private lateinit var usingFlowViewModel: UsingFlowViewModel
    private lateinit var connectivityManager: ConnectivityManager
    private lateinit var locationManager: LocationManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind = FrUsingFlowBinding.bind(view)

        setupUI()
        setupViewModel()
        setupFlow()

    }

    private fun setupUI() {
        connectivityManager = requireActivity().applicationContext
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        locationManager = requireActivity().applicationContext
            .getSystemService(LOCATION_SERVICE) as LocationManager
    }

    private fun setupViewModel() {
        usingFlowViewModel = ViewModelProviders.of(
            this,
            UsingFlowViewModelFactory(connectivityManager, locationManager)
        )[UsingFlowViewModel::class.java]
    }

    private fun setupFlow() {

    }

    override fun onDestroyView() {
        bind = null
        super.onDestroyView()
    }
}