package com.startandroid.newsapp.ui.mostpopular.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.terrakok.cicerone.Router
import com.startandroid.newsapp.R
import com.startandroid.newsapp.data.application.NewsApplication
import com.startandroid.newsapp.data.application.Screens
import com.startandroid.newsapp.data.model.PopularNewsItem
import com.startandroid.newsapp.databinding.FrMostPopularBinding
import com.startandroid.newsapp.ui.mostpopular.ItemForMostPopular
import com.startandroid.newsapp.ui.mostpopular.adapter.MostPopularAdapter
import com.startandroid.newsapp.ui.mostpopular.factory.MostPopularViewModelFactory
import com.startandroid.newsapp.ui.mostpopular.viewmodel.MostPopularViewModel
import com.startandroid.newsapp.utils.Status
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject

@InternalCoroutinesApi
class MostPopularFragment : Fragment(R.layout.fr_most_popular), ItemForMostPopular {

    private var bind: FrMostPopularBinding? = null
    private val binding get() = bind!!

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var mostPopularViewModelFactory: MostPopularViewModelFactory

    private val mostPopularViewModel: MostPopularViewModel by viewModels { mostPopularViewModelFactory }
    private lateinit var mostPopularAdapter: MostPopularAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        NewsApplication.INSTANCE.appComponent.inject(this)
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
        mostPopularAdapter = MostPopularAdapter(arrayListOf(), this)
        binding.apply {
            rvListMostPopular.layoutManager = layoutManager
            rvListMostPopular.addItemDecoration(
                DividerItemDecoration(
                    rvListMostPopular.context,
                    (rvListMostPopular.layoutManager as LinearLayoutManager).orientation
                )
            )
            rvListMostPopular.adapter = mostPopularAdapter
        }
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

    override fun openItemMoreDetails (popularNewsItem: PopularNewsItem) {
        router.navigateTo(Screens.MoreItemScreen(popularNewsItem))
    }

    override fun onDestroyView() {
        bind = null
        super.onDestroyView()
    }
}