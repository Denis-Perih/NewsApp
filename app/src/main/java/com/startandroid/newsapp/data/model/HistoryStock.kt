package com.startandroid.newsapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class HistoryStock(
    @SerializedName("dataset_data")
    val dataset_data: HistoryStockBlock
)

data class HistoryStockBlock(
    @SerializedName("data")
    val data: HistoryStockItem
)

@Parcelize
data class HistoryStockItem(
    @SerializedName("Date")
    val date: String,

    @SerializedName("Open")
    val open: Float,

    @SerializedName("High")
    val high: Float,

    @SerializedName("Low")
    val low: Float,

    @SerializedName("Close")
    val close: Float
): Parcelable

data class StockPrice(
    val stockDate: String,
    val data: Data) {
    data class Data(
        val open: Float,
        val high: Float,
        val low: Float,
        val close: Float)
}