package com.example.bookreport.data.model

import com.google.gson.annotations.SerializedName

data class Record (
    @SerializedName("num") val num: Int,
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("image") val image: String,
    @SerializedName("author") val author: String,
    @SerializedName("publisher") val publisher: String,
    @SerializedName("rating") val rating: Float,
    @SerializedName("memo") val memo: String,
    @SerializedName("date") val date: String
)