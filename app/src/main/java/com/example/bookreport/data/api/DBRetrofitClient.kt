package com.example.bookreport.data.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DBRetrofitClient {
    private val BASE_URL = "http://dbtest.dothome.co.kr/Android/"

    // 레트로핏 클라이언트 객체 반환
    fun getInstance(): Retrofit {
        var gson: Gson = GsonBuilder().setLenient().create() // json 응답을 객체로 변환하기 위해 필요

        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
    }

    val api: DBService = getInstance().create(DBService::class.java)
}