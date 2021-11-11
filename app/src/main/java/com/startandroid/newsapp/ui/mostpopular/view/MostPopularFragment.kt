package com.startandroid.newsapp.ui.mostpopular.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.startandroid.newsapp.R
import com.startandroid.newsapp.data.model.PopularNewsItem
import com.startandroid.newsapp.databinding.FrMostPopularBinding
import com.startandroid.newsapp.ui.main.MainContract
import com.startandroid.newsapp.ui.mostpopular.ItemForMostPopular
import com.startandroid.newsapp.ui.mostpopular.adapter.MostPopularAdapter
import com.startandroid.newsapp.ui.mostpopular.di.DaggerMostPopularComponent
import com.startandroid.newsapp.ui.mostpopular.factory.MostPopularViewModelFactory
import com.startandroid.newsapp.ui.mostpopular.viewmodel.MostPopularViewModel
import com.startandroid.newsapp.utils.Status
import javax.inject.Inject

class MostPopularFragment : Fragment(R.layout.fr_most_popular), ItemForMostPopular {

    private var bind: FrMostPopularBinding? = null
    private val binding get() = bind!!

    @Inject
    lateinit var mostPopularViewModelFactory: MostPopularViewModelFactory



    private val mostPopularViewModel: MostPopularViewModel by viewModels { mostPopularViewModelFactory }
    private lateinit var mostPopularAdapter: MostPopularAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        val daggerTopStoriesComponent = DaggerMostPopularComponent
            .builder()
            .build()
        daggerTopStoriesComponent.injectMostPopularFragment(this)
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind = FrMostPopularBinding.bind(view)

        setupUI()
        setupObserver()

    }

    private fun setupUI() {
        binding.srlSwipeContainer.setOnRefreshListener {
            setupObserver()
            binding.srlSwipeContainer.isRefreshing = false
        }

        val layoutManager =
            LinearLayoutManager(binding.rvListMostPopular.context, RecyclerView.VERTICAL, false)
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

    private fun setupObserver() {
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

    override fun openItemMoreDetails(popularNewsItem: PopularNewsItem) {
        val fm = parentFragment?.childFragmentManager
        fm?.saveFragmentInstanceState(this)
        (requireActivity() as MainContract).openPopularNewsMoreFragment(popularNewsItem)
    }

    override fun onDestroyView() {
        bind = null
        super.onDestroyView()
    }
}