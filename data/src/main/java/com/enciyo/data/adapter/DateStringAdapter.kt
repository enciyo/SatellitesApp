package com.enciyo.data.adapter

import com.enciyo.domain.model.DateString
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.text.SimpleDateFormat
import java.util.*

class DateStringAdapter {
    @ToJson
    fun toJson(@DateString date: String): String {
        return date
    }

    @FromJson
    @DateString
    fun fromJson(date: String): String {
        return try {
            val formattedDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                .parse(date)
            SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
                .format(formattedDate)
        } catch (e: Exception) {
            date
        }
    }
}