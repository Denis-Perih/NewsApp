package com.startandroid.newsapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class StoriesNews(
    @SerializedName("results")
    val results: List<StoriesNewsItem>
)

@Parcelize
data class StoriesNewsItem(
    @SerializedName("title")
    val title: String,

    @SerializedName("abstract")
    val abstract: String,

    @SerializedName("published_date")
    val published_date: String
) : Parcelable
