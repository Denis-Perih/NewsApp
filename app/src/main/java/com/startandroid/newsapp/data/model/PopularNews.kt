package com.startandroid.newsapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class PopularNews(
    @SerializedName("results")
    val results: List<PopularNewsItem>
)

@Parcelize
data class PopularNewsItem(
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