package com.startandroid.newsapp.ui.more

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.startandroid.newsapp.R
import com.startandroid.newsapp.data.model.PopularNewsItem
import com.startandroid.newsapp.data.model.StoriesNewsItem

class MoreItemFragment : Fragment() {

    lateinit var btnBackHome: Button
    lateinit var tvTitleMore: TextView
    lateinit var tvDateMore: TextView
    lateinit var tvAbstract: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val moreView: View = inflater.inflate(R.layout.fr_more_screen, container, false)

        btnBackHome = moreView.findViewById(R.id.btnBackHome)
        btnBackHome.setOnClickListener {
            val fm: FragmentManager = requireActivity().supportFragmentManager
            fm.popBackStack(MoreItemFragment::class.simpleName, FragmentManager.POP_BACK_STACK_INCLUSIVE) }

        tvTitleMore = moreView.findViewById(R.id.tvTitleMore)
        tvDateMore = moreView.findViewById(R.id.tvDateMore)
        tvAbstract = moreView.findViewById(R.id.tvAbstract)

        setDataToPost()

        return moreView
    }

    private fun setDataToPost() {
        val bundle: Bundle? = arguments
        if (bundle != null) {
            val popularNewsItem: PopularNewsItem? = bundle.getParcelable("popularNewsItem")
            val storiesItem: StoriesNewsItem? = bundle.getParcelable("storiesNewsItem")
            if (popularNewsItem == null) {
                tvTitleMore.text = storiesItem?.title
                tvDateMore.text = storiesItem?.published_date
                tvAbstract.text = storiesItem?.abstract
            } else {
                tvTitleMore.text = popularNewsItem.title
                tvDateMore.text = popularNewsItem.published_date
                tvAbstract.text = popularNewsItem.abstract
            }
        }
    }
}