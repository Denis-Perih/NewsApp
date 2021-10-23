package com.startandroid.newsapp.data.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class Stories(
    @SerializedName("results")
    val results: List<StoriesItem>
)

@Parcelize
data class StoriesItem(
    @SerializedName("title")
    val title: String,

    @SerializedName("abstract")
    val abstract: String,

    @SerializedName("published_date")
    val published_date: String
) : Parcelable
