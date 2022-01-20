package com.example.bookreport.data.api

import com.example.bookreport.data.model.Book
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    // 요청 메소드
    // @Path("동적으로 변하는 부분의 URL")
    @GET("search/{type}")
    suspend fun getSearchBook(
        @Path("type") type: String,
        @Query("query") query: String,
    ): Response<Book> // <>안에 성공적으로 응답될 bocy타입의 data class를 넣는다.
}