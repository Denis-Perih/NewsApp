package com.startandroid.newsapp.tab1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.startandroid.newsapp.R
import com.startandroid.newsapp.data.NewsRepository
import com.startandroid.newsapp.data.entity.News
import com.startandroid.newsapp.main.MainContract
import com.startandroid.newsapp.signin.SignInContract
import com.startandroid.newsapp.signin.SignInPresenter
import com.startandroid.newsapp.tab1.adapter.RecyclerViewAdapter
import io.reactivex.Single

class TabOneFragment : Fragment(), TabOneContract.View {

    private val presenter: TabOneContract.Presenter = TabOnePresenter(this)

    lateinit var srlSwipeContainer: SwipeRefreshLayout
    lateinit var rvListMostPopular: RecyclerView

    lateinit var oneView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        oneView = inflater.inflate(R.layout.fr_tab1, container, false)

        srlSwipeContainer = oneView.findViewById(R.id.srlSwipeContainer)
        srlSwipeContainer.setOnRefreshListener { context?.let { presenter.startFragment(it) } }

        context?.let { presenter.startFragment(it) }

        return oneView
    }

    override fun noNetConnection() {
        (requireActivity() as MainContract).noNetConnected()
    }

    override fun buildListNews() {
        rvListMostPopular = oneView.findViewById(R.id.rvListMostPopular)
        val layoutManager = LinearLayoutManager(rvListMostPopular.context, RecyclerView.VERTICAL, false)
        rvListMostPopular.layoutManager = layoutManager
        srlSwipeContainer.isRefreshing = false
    }

    override fun setAdapter(rvAdapter: RecyclerViewAdapter) {
        rvListMostPopular.adapter = rvAdapter
    }
}