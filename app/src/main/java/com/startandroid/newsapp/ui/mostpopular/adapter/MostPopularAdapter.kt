package com.startandroid.newsapp.ui.mostpopular.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.startandroid.newsapp.data.model.PopularNews
import com.startandroid.newsapp.data.model.PopularNewsItem
import com.startandroid.newsapp.databinding.ItemMostPopularBinding
import com.startandroid.newsapp.ui.mostpopular.ItemForMostPopular
import com.startandroid.newsapp.utils.extensions.inflateViewBinding
import com.startandroid.newsapp.utils.extensions.viewBinding

class MostPopularAdapter(
    private var blockNewsData: List<PopularNewsItem>,
    private val itemForMostPopular: ItemForMostPopular
) : RecyclerView.Adapter<MostPopularAdapter.MostPopularHolder>() {

    class MostPopularHolder(
        private val itemMostPopularBinding: ItemMostPopularBinding,
        private val itemForMostPopular: ItemForMostPopular
    ) : RecyclerView.ViewHolder(itemMostPopularBinding.root) {
        fun bind(popularNewsItem: PopularNewsItem) {
            itemMostPopularBinding.tvTitleItem.text = popularNewsItem.title
            itemMostPopularBinding.tvPublishedDateItem.text =
                popularNewsItem.published_date?.substring(0, 10)
            itemMostPopularBinding.tvSourceItem.text = popularNewsItem.source
            itemMostPopularBinding.cvItemTabOneTwo.setOnClickListener {
                // add answer for details
                itemForMostPopular.openItemMoreDetails(popularNewsItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MostPopularHolder {
//        val itemMostPopularBinding = parent.inflateViewBinding<ItemMostPopularBinding>()
        return MostPopularHolder(
            parent.viewBinding(ItemMostPopularBinding::inflate),
            itemForMostPopular
        )
    }

    override fun onBindViewHolder(holder: MostPopularHolder, position: Int) {
        holder.bind(blockNewsData[position])
    }

    override fun getItemCount(): Int {
        return blockNewsData.size
    }

    fun addData(popularNews: PopularNews) {
        blockNewsData = popularNews.results
    }

}