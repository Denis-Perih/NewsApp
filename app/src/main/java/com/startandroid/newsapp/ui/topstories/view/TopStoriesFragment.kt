package com.startandroid.newsapp.ui.topstories.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.startandroid.newsapp.R
import com.startandroid.newsapp.data.model.StoriesNewsItem
import com.startandroid.newsapp.databinding.FrTopStoriesBinding
import com.startandroid.newsapp.ui.main.MainContract
import com.startandroid.newsapp.ui.topstories.ItemForTopStories
import com.startandroid.newsapp.ui.topstories.adapter.TopStoriesAdapter
import com.startandroid.newsapp.ui.topstories.di.DaggerTopStoriesComponent
import com.startandroid.newsapp.ui.topstories.factory.TopStoriesViewModelFactory
import com.startandroid.newsapp.ui.topstories.viewmodel.TopStoriesViewModel
import com.startandroid.newsapp.utils.Status
import javax.inject.Inject

class TopStoriesFragment : Fragment(R.layout.fr_top_stories), ItemForTopStories {

    private var bind: FrTopStoriesBinding? = null
    private val binding get() = bind!!

    @Inject
    lateinit var topStoriesViewModelFactory: TopStoriesViewModelFactory

    private val topStoriesViewModel: TopStoriesViewModel by viewModels { topStoriesViewModelFactory }
    private lateinit var topStoriesAdapter: TopStoriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        val daggerTopStoriesComponent = DaggerTopStoriesComponent
            .builder()
            .build()
        daggerTopStoriesComponent.injectTopStoriesFragment(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind = FrTopStoriesBinding.bind(view)

        setupUI()
        setupObserver()
    }

    private fun setupUI() {

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

    private fun setupObserver() {
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