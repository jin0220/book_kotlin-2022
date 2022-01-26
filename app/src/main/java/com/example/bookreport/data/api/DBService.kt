package com.example.bookreport.data.api

import com.example.bookreport.data.model.Record
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*
import kotlin.collections.HashMap

interface DBService {
    @POST("/bookMemo.php")
    suspend fun recordInsert(
        @Body record: Record
    ): Call<Record>
}