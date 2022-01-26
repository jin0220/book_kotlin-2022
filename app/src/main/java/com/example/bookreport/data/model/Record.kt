package com.example.bookreport.data.model

import com.google.gson.annotations.SerializedName

data class Record (
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("image") val image: String,
    @SerializedName("author") val author: String,
    @SerializedName("publisher") val publisher: String,
    @SerializedName("rating") val rating: Double,
    @SerializedName("memo") val memo: String,
//        @SerializedName("date") val date: String
)