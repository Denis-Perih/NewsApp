package com.startandroid.newsapp.ui.historystock.view

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.startandroid.newsapp.R
import com.startandroid.newsapp.data.model.HistoryStockItem
import com.startandroid.newsapp.databinding.FrHistoryStockBinding
import com.startandroid.newsapp.ui.historystock.factory.HistoryStockViewModelFactory
import com.startandroid.newsapp.ui.historystock.viewmodel.HistoryStockViewModel
import com.startandroid.newsapp.utils.Status

class HistoryStockFragment : Fragment(R.layout.fr_history_stock) {

    private var bind: FrHistoryStockBinding? = null
    private val binding get() = bind!!

    private lateinit var historyStockViewModel: HistoryStockViewModel
    private lateinit var connectivityManager: ConnectivityManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind = FrHistoryStockBinding.bind(view)

        setupUI()
        setupViewModel()
        setupObserver()
    }

    private fun setupUI() {
        connectivityManager = requireActivity().applicationContext
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    private fun setupViewModel() {
        historyStockViewModel = ViewModelProviders.of(
            this,
            HistoryStockViewModelFactory(connectivityManager)
        ).get(HistoryStockViewModel::class.java)
    }

    private fun setupObserver() {
        historyStockViewModel.getHistory().observe(viewLifecycleOwner, Observer {
            when(it.status) {
                Status.SUCCESS -> {
                    it.data?.let { it1 -> updateData(it1) }
                }
                Status.ERROR -> {
                    // error UI
                }
            }
        })
    }

    private fun updateData(data: HistoryStockItem) {
        if (data.date != "") {
            binding.tvDateValue.text = data.date
            binding.tvOpenValue.text = data.open.toString()
            binding.tvHighValue.text = data.high.toString()
            binding.tvLowValue.text = data.low.toString()
            binding.tvCloseValue.text = data.close.toString()
        }
    }

    override fun onDestroy() {
        bind = null
        super.onDestroy()
    }
}