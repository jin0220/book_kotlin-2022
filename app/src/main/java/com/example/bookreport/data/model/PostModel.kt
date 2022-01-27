package com.example.bookreport.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PostModel(
        @Expose
        @SerializedName("success")
        val success: String
)