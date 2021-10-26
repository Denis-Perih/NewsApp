package com.startandroid.newsapp.ui.topstories.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.startandroid.newsapp.R
import com.startandroid.newsapp.data.model.StoriesNews
import com.startandroid.newsapp.data.model.StoriesNewsItem
import com.startandroid.newsapp.ui.topstories.ItemForTopStories

class TopStoriesAdapter(private var blockNewsData: List<StoriesNewsItem>,
                              private val itemForTopStories: ItemForTopStories
) : RecyclerView.Adapter<TopStoriesAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitleItem: TextView
        val tvPublishedDateItem: TextView
        val cvItemTabTwo: CardView

        init {
            tvTitleItem = itemView.findViewById(R.id.tvTitleItem)
            tvPublishedDateItem = itemView.findViewById(R.id.tvPublishedDateItem)
            cvItemTabTwo = itemView.findViewById(R.id.cvItemTabTwo)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_tab_2, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.cvItemTabTwo.setOnClickListener {
            // add answer for details
            itemForTopStories.openItemMoreDetails(blockNewsData.get(position))
        }
        setDataToCard(holder, position)
    }

    private fun setDataToCard(holder: ViewHolder, position: Int) {
        holder.tvTitleItem.text = blockNewsData.get(position).title
        holder.tvPublishedDateItem.text = blockNewsData.get(position).published_date.substring(0, 10)
    }

    override fun getItemCount(): Int {
        return blockNewsData.size
    }

    fun addData(storiesNews: StoriesNews) {
        blockNewsData = storiesNews.results
    }
}