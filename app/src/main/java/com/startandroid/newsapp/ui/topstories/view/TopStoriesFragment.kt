package com.startandroid.newsapp.ui.topstories.view

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
import com.startandroid.newsapp.data.model.StoriesNewsItem
import com.startandroid.newsapp.databinding.FrTopStoriesBinding
import com.startandroid.newsapp.ui.main.MainContract
import com.startandroid.newsapp.ui.topstories.ItemForTopStories
import com.startandroid.newsapp.ui.topstories.adapter.TopStoriesAdapter
import com.startandroid.newsapp.ui.topstories.factory.TopStoriesViewModelFactory
import com.startandroid.newsapp.ui.topstories.viewmodel.TopStoriesViewModel
import com.startandroid.newsapp.utils.Status

class TopStoriesFragment : Fragment(R.layout.fr_top_stories), ItemForTopStories {

    private var bind: FrTopStoriesBinding? = null
    private val binding get() = bind!!

    private lateinit var topStoriesViewModel: TopStoriesViewModel
    private lateinit var topStoriesAdapter: TopStoriesAdapter
    private lateinit var connectivityManager: ConnectivityManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind = FrTopStoriesBinding.bind(view)

        setupUI()
        setupViewModel()
        setupObserver()
    }

    private fun setupUI() {
        connectivityManager = requireActivity().applicationContext
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        binding.srlSwipeContainerTab2.setOnRefreshListener {
            setupObserver()
            binding.srlSwipeContainerTab2.isRefreshing = false
        }

        val layoutManager = LinearLayoutManager(binding.rvListTopStories.context, RecyclerView.VERTICAL, false)
        binding.rvListTopStories.layoutManager = layoutManager
        topStoriesAdapter = TopStoriesAdapter(arrayListOf(), this)
        binding.rvListTopStories.addItemDecoration(
            DividerItemDecoration(
                binding.rvListTopStories.context,
                (binding.rvListTopStories.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.rvListTopStories.adapter = topStoriesAdapter
    }

    private fun setupViewModel() {
        topStoriesViewModel = ViewModelProviders.of(
            this,
            TopStoriesViewModelFactory(connectivityManager)
        ).get(TopStoriesViewModel::class.java)
    }

    private fun setupObserver() {
        if (topStoriesViewModel.getTopStoriesNet().value == "Not net") {
            (requireActivity() as MainContract).noNetConnected()
        } else {
            topStoriesViewModel.getTopStories().observe(viewLifecycleOwner, Observer {
                when (it.status) {
                    Status.SUCCESS -> {
                        it.data?.let { it1 -> topStoriesAdapter.addData(it1) }
                        topStoriesAdapter.notifyDataSetChanged()
                    }
                    Status.ERROR -> {
                        //Error
                    }
                }
            })
        }
    }

    override fun openItemMoreDetails(storiesNewsItem: StoriesNewsItem) {
        val fm = parentFragment?.childFragmentManager
        fm?.saveFragmentInstanceState(this)
        (requireActivity() as MainContract).openTopStoriesMoreFragment(storiesNewsItem)
    }

    override fun onDestroyView() {
        bind = null
        super.onDestroyView()
    }
}