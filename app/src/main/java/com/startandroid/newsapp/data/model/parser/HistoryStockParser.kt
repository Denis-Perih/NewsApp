package com.startandroid.newsapp.data.model.parser

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.startandroid.newsapp.data.model.HistoryStock
import com.startandroid.newsapp.data.model.HistoryStockItem
import java.lang.reflect.Type

class HistoryStockParser : JsonDeserializer<HistoryStock> {

    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?): HistoryStock {
        val dataArray = json.asJsonObject.getAsJsonObject("dataset_data").getAsJsonArray("data")
        dataArray.firstOrNull()?.let {
            with(it.asJsonArray) {
                val date = this[0].asString
                val open = this[1].asFloat
                val high = this[2].asFloat
                val low = this[3].asFloat
                val close = this[4].asFloat
                return HistoryStock(HistoryStockItem(date , open, high, low, close))
            }
        } ?: kotlin.run {
            return HistoryStock(HistoryStockItem("",0.0f, 0.0f, 0.0f, 0.0f))
        }
    }

}