package com.example.bookreport.data.api

import com.example.bookreport.data.model.Record
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*
import kotlin.collections.HashMap

interface DBService {
    @FormUrlEncoded
    @POST("bookMemo.php")
    fun recordInsert(
        @Field("id") id: String,
        @Field("title") title: String,
        @Field("image") image: String,
        @Field("author") author: String,
        @Field("publisher") publisher: String,
        @Field("rating") rating: Double,
        @Field("memo") memo: String,
    ): Call<Record>
}