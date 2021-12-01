package com.startandroid.newsapp.ui.more

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.github.terrakok.cicerone.Router
import com.startandroid.newsapp.R
import com.startandroid.newsapp.data.application.NewsApplication
import com.startandroid.newsapp.data.application.Screens
import com.startandroid.newsapp.data.model.PopularNewsItem
import com.startandroid.newsapp.data.model.StoriesNewsItem
import com.startandroid.newsapp.databinding.FrMoreScreenBinding
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject
import kotlin.properties.Delegates

@InternalCoroutinesApi
class MoreItemFragment : Fragment(R.layout.fr_more_screen) {

    private var bind: FrMoreScreenBinding? = null
    private val binding get() = bind!!

    @Inject
    lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        NewsApplication.INSTANCE.appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind = FrMoreScreenBinding.bind(view)

        binding.btnBackHome.setOnClickListener {
            router.backTo(Screens.HomeScreen())
        }

        setDataToPost()
    }

    private fun setDataToPost() {
        val popularNewsItem: PopularNewsItem? = requireArguments().getParcelable(ARG_POPULAR_NEWS_ITEM)
        val storiesItem: StoriesNewsItem? = requireArguments().getParcelable(ARG_STORIES_NEWS_ITEM)
        if (storiesItem != null) {
            binding.apply {
                tvTitleMore.text = storiesItem.title
                tvDateMore.text = storiesItem.published_date.substring(0, 10)
                tvAbstract.text = storiesItem.abstract
            }
        } else {
            binding.apply {
                tvTitleMore.text = popularNewsItem?.title
                tvDateMore.text = popularNewsItem?.published_date?.substring(0, 10)
                tvAbstract.text = popularNewsItem?.abstract
            }
        }
    }

    override fun onDestroyView() {
        bind = null
        super.onDestroyView()
    }


    companion object {
        private const val ARG_POPULAR_NEWS_ITEM = "arg_popular_news_item"
        private const val ARG_STORIES_NEWS_ITEM = "arg_stories_news_item"
        private val bundle = Bundle()

        fun newInstance(item: PopularNewsItem) = MoreItemFragment().apply {
            Log.d("TAG_BUNDLE", "newInstance: ${bundle}")
            bundle.putParcelable(ARG_POPULAR_NEWS_ITEM, item)
            Log.d("TAG_BUNDLE", "newInstance: $bundle")
            arguments = bundle
        }

        fun newInstance(item: StoriesNewsItem) = MoreItemFragment().apply {
            bundle.putParcelable(ARG_STORIES_NEWS_ITEM, item)
            arguments = bundle
        }
    }
}