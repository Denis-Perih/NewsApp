package com.startandroid.newsapp.ui.mostpopular.view

import android.content.Context
import android.net.ConnectivityManager
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.PrecomputedTextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.startandroid.newsapp.R
import com.startandroid.newsapp.data.model.PopularNewsItem
import com.startandroid.newsapp.ui.main.MainContract
import com.startandroid.newsapp.ui.more.MoreItemFragment
import com.startandroid.newsapp.ui.mostpopular.ItemForMostPopular
import com.startandroid.newsapp.ui.mostpopular.adapter.MostPopularAdapter
import com.startandroid.newsapp.ui.mostpopular.factory.MostPopularViewModelFactory
import com.startandroid.newsapp.ui.mostpopular.viewmodel.MostPopularViewModel
import com.startandroid.newsapp.utils.Status

class MostPopularFragment : Fragment(), ItemForMostPopular {

    private lateinit var srlSwipeContainer: SwipeRefreshLayout
    private lateinit var rvListMostPopular: RecyclerView

    private lateinit var mostPopularViewModel: MostPopularViewModel
    private lateinit var mostPopularAdapter: MostPopularAdapter
    private lateinit var connectivityManager: ConnectivityManager

    lateinit var oneView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        oneView = inflater.inflate(R.layout.fr_most_popular, container, false)

        setupUI()
        setupViewModel()
        setupObserver()

        return oneView
    }

    private fun setupUI() {
        connectivityManager = requireActivity().applicationContext
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        srlSwipeContainer = oneView.findViewById(R.id.srlSwipeContainer)
        srlSwipeContainer.setOnRefreshListener {
            setupObserver()
            srlSwipeContainer.isRefreshing = false
        }

        rvListMostPopular = oneView.findViewById(R.id.rvListMostPopular)
        val layoutManager = LinearLayoutManager(rvListMostPopular.context, RecyclerView.VERTICAL, false)
        rvListMostPopular.layoutManager = layoutManager
        mostPopularAdapter = MostPopularAdapter(arrayListOf(), MostPopularFragment())
        rvListMostPopular.addItemDecoration(
            DividerItemDecoration(
                rvListMostPopular.context,
                (rvListMostPopular.layoutManager as LinearLayoutManager).orientation
            )
        )
        rvListMostPopular.adapter = mostPopularAdapter
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
}