package com.startandroid.newsapp.data.entity

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class News(
    @SerializedName("results")
    val results: List<NewsItem>
)

@Parcelize
data class NewsItem(
    @SerializedName("id")
    val id: Long,

    @SerializedName("source")
    val source: String?,

    @SerializedName("published_date")
    val published_date: String?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("abstract")
    val abstract: String?
) : Parcelable