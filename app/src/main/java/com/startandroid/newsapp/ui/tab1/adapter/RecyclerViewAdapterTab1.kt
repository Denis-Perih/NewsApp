package com.startandroid.newsapp.ui.tab1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.startandroid.newsapp.R
import com.startandroid.newsapp.data.entity.NewsItem
import com.startandroid.newsapp.ui.tab1.AnswerForMoreDetailsTab1

class RecyclerViewAdapterTab1(private val blockNewsData: List<NewsItem>,
                              private val answerMoreTab1: AnswerForMoreDetailsTab1): RecyclerView.Adapter<RecyclerViewAdapterTab1.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitleItem: TextView
        val tvPublishedDateItem: TextView
        val tvSourceItem: TextView
        val cvItemTabOneTwo: CardView

        init {
            tvTitleItem = itemView.findViewById(R.id.tvTitleItem)
            tvPublishedDateItem = itemView.findViewById(R.id.tvPublishedDateItem)
            tvSourceItem = itemView.findViewById(R.id.tvSourceItem)
            cvItemTabOneTwo = itemView.findViewById(R.id.cvItemTabOneTwo)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.item_tab_1, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.cvItemTabOneTwo.setOnClickListener {
            // add answer for details
            answerMoreTab1.onSuccessMoreDetails(blockNewsData.get(position))
        }
        setDataToCard(holder, position)
    }

    private fun setDataToCard(holder: ViewHolder, position: Int) {
        holder.tvTitleItem.text = blockNewsData.get(position).title
        holder.tvPublishedDateItem.text = blockNewsData.get(position).published_date
        holder.tvSourceItem.text = blockNewsData.get(position).source
    }

    override fun getItemCount(): Int {
        return blockNewsData.size
    }

}