package com.startandroid.newsapp.ui.historystock.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.startandroid.newsapp.R
import com.startandroid.newsapp.data.application.DaggerNewsAppApplicationComponent
import com.startandroid.newsapp.data.application.NewsAppApplicationComponent
import com.startandroid.newsapp.data.application.NewsApplication
import com.startandroid.newsapp.data.model.HistoryStockItem
import com.startandroid.newsapp.databinding.FrHistoryStockBinding
import com.startandroid.newsapp.ui.historystock.factory.HistoryStockViewModelFactory
import com.startandroid.newsapp.ui.historystock.viewmodel.HistoryStockViewModel
import com.startandroid.newsapp.utils.Status
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import javax.inject.Inject

@ObsoleteCoroutinesApi
class HistoryStockFragment : Fragment(R.layout.fr_history_stock) {

    private var bind: FrHistoryStockBinding? = null
    private val binding get() = bind!!

    @Inject
    lateinit var viewModelFactory: HistoryStockViewModelFactory

    private val historyStockViewModel: HistoryStockViewModel by viewModels {viewModelFactory}

    override fun onCreate(savedInstanceState: Bundle?) {
        NewsApplication.INSTANCE.appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind = FrHistoryStockBinding.bind(view)

        setupObserver()
    }

    private fun setupObserver() {
        historyStockViewModel.getHistory().observe(viewLifecycleOwner, Observer {
            when (it.status) {
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

    override fun onDestroyView() {
        bind = null
        super.onDestroyView()
    }
}