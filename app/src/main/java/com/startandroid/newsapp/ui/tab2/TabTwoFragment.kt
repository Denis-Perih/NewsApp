package com.startandroid.newsapp.ui.tab2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.startandroid.newsapp.R
import com.startandroid.newsapp.data.entity.StoriesItem
import com.startandroid.newsapp.ui.main.MainContract
import com.startandroid.newsapp.ui.tab2.adapter.RecyclerViewAdapterTab2

class TabTwoFragment : Fragment(), TabTwoContract.View {

    private val presenter: TabTwoContract.Presenter = TabTwoPresenter(this)

    lateinit var srlSwipeContainerTab2: SwipeRefreshLayout
    lateinit var rvListTopStories: RecyclerView

    lateinit var twoView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        twoView = inflater.inflate(R.layout.fr_tab2, container, false)

        srlSwipeContainerTab2 = twoView.findViewById(R.id.srlSwipeContainerTab2)
        srlSwipeContainerTab2.setOnRefreshListener { context?.let { it1 -> presenter.startFragment(it1) } }

        context?.let { presenter.startFragment(it) }

        return twoView
    }

    override fun buildListNews() {
        rvListTopStories = twoView.findViewById(R.id.rvListTopStories)
        val layoutManager = LinearLayoutManager(rvListTopStories.context, RecyclerView.VERTICAL, false)
        rvListTopStories.layoutManager = layoutManager
        srlSwipeContainerTab2.isRefreshing = false
    }

    override fun noNetConnection() {
        (requireActivity() as MainContract).noNetConnected()
    }

    override fun setAdapter(rvAdapterTab2: RecyclerViewAdapterTab2) {
        rvListTopStories.adapter = rvAdapterTab2
    }

    override fun openTab2FragmentMoreDetails(storiesItem: StoriesItem) {
        val fm: FragmentManager = requireActivity().supportFragmentManager
        fm.saveFragmentInstanceState(this)
        (requireActivity() as MainContract).openTab2MoreDetailsFragment(storiesItem)
    }
}