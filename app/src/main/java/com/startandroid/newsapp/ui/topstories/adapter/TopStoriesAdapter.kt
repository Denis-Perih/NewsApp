package com.startandroid.newsapp.ui.topstories.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.startandroid.newsapp.data.model.StoriesNews
import com.startandroid.newsapp.data.model.StoriesNewsItem
import com.startandroid.newsapp.databinding.ItemTopStoriesBinding
import com.startandroid.newsapp.ui.topstories.ItemForTopStories

class TopStoriesAdapter(private var blockNewsData: List<StoriesNewsItem>,
                              private val itemForTopStories: ItemForTopStories
) : RecyclerView.Adapter<TopStoriesAdapter.TopStoriesHolder>() {

    class TopStoriesHolder(private val itemTopStoriesBinding: ItemTopStoriesBinding,
                           private val itemForTopStories: ItemForTopStories
                           ) : RecyclerView.ViewHolder(itemTopStoriesBinding.root) {
        fun bind(storiesNewsItem: StoriesNewsItem) {
            itemTopStoriesBinding.tvTitleItem.text = storiesNewsItem.title
            itemTopStoriesBinding.tvPublishedDateItem.text = storiesNewsItem.published_date.substring(0, 10)
            itemTopStoriesBinding.cvItemTabTwo.setOnClickListener {
                // add answer for details
                itemForTopStories.openItemMoreDetails(storiesNewsItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopStoriesHolder {
        val itemTopStoriesBinding = ItemTopStoriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopStoriesHolder(itemTopStoriesBinding, itemForTopStories)
    }

    override fun onBindViewHolder(holder: TopStoriesHolder, position: Int) {
        holder.bind(blockNewsData[position])
    }

    override fun getItemCount(): Int {
        return blockNewsData.size
    }

    fun addData(storiesNews: StoriesNews) {
        blockNewsData = storiesNews.results
    }
}