package com.example.bookreport.data.api

import androidx.lifecycle.MutableLiveData
import com.example.bookreport.data.model.Calendar
import com.example.bookreport.data.model.PostModel
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
        @Field("rating") rating: Float,
        @Field("memo") memo: String,
    ): Call<Record>

    @FormUrlEncoded
    @POST("my-record.php")
    fun recordSelect(
        @Field("id") id: String,
        @Field("date") date: String
    ): Call<MutableList<Record>>

    @FormUrlEncoded
    @POST("calendarList.php")
    fun calendarSelect(
        @Field("id") id: String,
        @Field("date") date: String,
    ): Call<MutableList<Calendar>>

    @FormUrlEncoded
    @POST("memoDelete.php")
    fun recordDelete(
        @Field("num") num: Int,
    ): Call<MutableList<PostModel>>

    @FormUrlEncoded
    @POST("memoUpdate.php")
    fun recordUpdate(
            @Field("num") num: Int,
            @Field("title") title: String,
            @Field("image") image: String,
            @Field("author") author: String,
            @Field("publisher") publisher: String,
            @Field("rating") rating: Float,
            @Field("memo") memo: String,
            @Field("date") date: String
    ): Call<MutableList<PostModel>>
}