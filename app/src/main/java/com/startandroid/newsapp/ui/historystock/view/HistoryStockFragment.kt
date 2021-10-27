package com.startandroid.newsapp.ui.historystock.view

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.startandroid.newsapp.R
import com.startandroid.newsapp.data.model.HistoryStockItem
import com.startandroid.newsapp.ui.historystock.factory.HistoryStockViewModelFactory
import com.startandroid.newsapp.ui.historystock.viewmodel.HistoryStockViewModel
import com.startandroid.newsapp.utils.Status

class HistoryStockFragment : Fragment() {

    private lateinit var historyStockViewModel: HistoryStockViewModel
    private lateinit var connectivityManager: ConnectivityManager

    private lateinit var tvDateValue: TextView
    private lateinit var tvOpenValue: TextView
    private lateinit var tvHighValue: TextView
    private lateinit var tvLowValue: TextView
    private lateinit var tvCloseValue: TextView

    lateinit var historyView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        historyView = inflater.inflate(R.layout.fr_history_stock, container, false)

        setupUI()
        setupViewModel()
        setupObserver()

        return historyView
    }

    private fun setupUI() {
        connectivityManager = requireActivity().applicationContext
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        tvDateValue = historyView.findViewById(R.id.tvDateValue)
        tvOpenValue = historyView.findViewById(R.id.tvOpenValue)
        tvHighValue = historyView.findViewById(R.id.tvHighValue)
        tvLowValue = historyView.findViewById(R.id.tvLowValue)
        tvCloseValue = historyView.findViewById(R.id.tvCloseValue)

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
            tvDateValue.text = data.date
            tvOpenValue.text = data.open.toString()
            tvHighValue.text = data.high.toString()
            tvLowValue.text = data.low.toString()
            tvCloseValue.text = data.close.toString()
        }
    }
}