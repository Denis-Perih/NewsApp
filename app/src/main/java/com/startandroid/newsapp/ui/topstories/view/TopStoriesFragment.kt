package com.startandroid.newsapp.ui.topstories.view

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.startandroid.newsapp.R
import com.startandroid.newsapp.data.model.StoriesNewsItem
import com.startandroid.newsapp.ui.main.MainContract
import com.startandroid.newsapp.ui.topstories.ItemForTopStories
import com.startandroid.newsapp.ui.topstories.adapter.TopStoriesAdapter
import com.startandroid.newsapp.ui.topstories.factory.TopStoriesViewModelFactory
import com.startandroid.newsapp.ui.topstories.viewmodel.TopStoriesViewModel
import com.startandroid.newsapp.utils.Status

class TopStoriesFragment : Fragment(), ItemForTopStories {

    private lateinit var srlSwipeContainerTab2: SwipeRefreshLayout
    private lateinit var rvListTopStories: RecyclerView

    private lateinit var topStoriesViewModel: TopStoriesViewModel
    private lateinit var topStoriesAdapter: TopStoriesAdapter
    private lateinit var connectivityManager: ConnectivityManager

    lateinit var twoView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        twoView = inflater.inflate(R.layout.fr_top_stories, container, false)

        setupUI()
        setupViewModel()
        setupObserver()

        return twoView
    }

    private fun setupUI() {
        connectivityManager = requireActivity().applicationContext
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        srlSwipeContainerTab2 = twoView.findViewById(R.id.srlSwipeContainerTab2)
        srlSwipeContainerTab2.setOnRefreshListener {
            setupObserver()
            srlSwipeContainerTab2.isRefreshing = false
        }

        rvListTopStories = twoView.findViewById(R.id.rvListTopStories)
        val layoutManager = LinearLayoutManager(rvListTopStories.context, RecyclerView.VERTICAL, false)
        rvListTopStories.layoutManager = layoutManager
        topStoriesAdapter = TopStoriesAdapter(arrayListOf(), this)
        rvListTopStories.addItemDecoration(
            DividerItemDecoration(
                rvListTopStories.context,
                (rvListTopStories.layoutManager as LinearLayoutManager).orientation
            )
        )
        rvListTopStories.adapter = topStoriesAdapter
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
        val fm = requireActivity().supportFragmentManager
        fm.saveFragmentInstanceState(this)
        (requireActivity() as MainContract).openTopStoriesMoreFragment(storiesNewsItem)
    }
}