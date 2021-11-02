package com.startandroid.newsapp.ui.mostpopular.view

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.startandroid.newsapp.R
import com.startandroid.newsapp.data.model.PopularNewsItem
import com.startandroid.newsapp.databinding.FrMostPopularBinding
import com.startandroid.newsapp.ui.main.MainContract
import com.startandroid.newsapp.ui.mostpopular.ItemForMostPopular
import com.startandroid.newsapp.ui.mostpopular.adapter.MostPopularAdapter
import com.startandroid.newsapp.ui.mostpopular.factory.MostPopularViewModelFactory
import com.startandroid.newsapp.ui.mostpopular.viewmodel.MostPopularViewModel
import com.startandroid.newsapp.utils.Status

class MostPopularFragment : Fragment(R.layout.fr_most_popular), ItemForMostPopular {

    private var bind: FrMostPopularBinding? = null
    private val binding get() = bind!!

    private lateinit var mostPopularViewModel: MostPopularViewModel
    private lateinit var mostPopularAdapter: MostPopularAdapter
    private lateinit var connectivityManager: ConnectivityManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind = FrMostPopularBinding.bind(view)

        setupUI()
        setupViewModel()
        setupObserver()
    }

    private fun setupUI() {
        connectivityManager = requireActivity().applicationContext
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        binding.srlSwipeContainer.setOnRefreshListener {
            setupObserver()
            binding.srlSwipeContainer.isRefreshing = false
        }

        val layoutManager = LinearLayoutManager(binding.rvListMostPopular.context, RecyclerView.VERTICAL, false)
        binding.rvListMostPopular.layoutManager = layoutManager
        mostPopularAdapter = MostPopularAdapter(arrayListOf(), this)
        binding.rvListMostPopular.addItemDecoration(
            DividerItemDecoration(
                binding.rvListMostPopular.context,
                (binding.rvListMostPopular.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.rvListMostPopular.adapter = mostPopularAdapter
    }

    private fun setupViewModel() {
        mostPopularViewModel = ViewModelProviders.of(
            this,
            MostPopularViewModelFactory(connectivityManager)
        ).get(MostPopularViewModel::class.java)
    }

    private fun setupObserver() {
        if (mostPopularViewModel.getMostPopularNet().value == "Not net") {
            (requireActivity() as MainContract).noNetConnected()
        } else {
            mostPopularViewModel.getMostPopular().observe(viewLifecycleOwner, Observer {
                when (it.status) {
                    Status.SUCCESS -> {
                        it.data?.let { it1 -> mostPopularAdapter.addData(it1) }
                        mostPopularAdapter.notifyDataSetChanged()
                    }
                    Status.ERROR -> {
                        //Error
                    }
                }
            })
        }
    }

    override fun openItemMoreDetails(popularNewsItem: PopularNewsItem) {
        val fm = requireActivity().supportFragmentManager
        fm.saveFragmentInstanceState(this)
        (requireActivity() as MainContract).openPopularNewsMoreFragment(popularNewsItem)
    }

    override fun onDestroyView() {
        bind = null
        super.onDestroyView()
    }
}