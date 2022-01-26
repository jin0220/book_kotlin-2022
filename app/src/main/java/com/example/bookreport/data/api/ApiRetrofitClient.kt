package com.example.bookreport.data.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiRetrofitClient {
    private val BASE_URL = "https://openapi.naver.com/v1/"
    private val CLIENT_ID = "7uNzbB6KkWj7kQINUlFi"
    private val CLIENT_SECRET = "O8cwyE7Wh2"

    // 레트로핏 클라이언트 객체 반환
    fun getInstance(): Retrofit{
        var gson: Gson = GsonBuilder().setLenient().create() // json 응답을 객체로 변환하기 위해 필요

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }

    // 생성된 클라이언트를 이용하여, Http API 명세가 담긴 Interface의 구현체를 생성한 뒤 반환
    // Apiservice 구현체
    fun getApiService(): ApiService{
        return getInstance().create(ApiService::class.java)
    }

    // api 요청 시 api 요청 헤더에 클라이언트 id와 키를 보내기
    val headerInterceptor = Interceptor {
        val request = it.request()
            .newBuilder()
            .addHeader("X-Naver-Client-Id", CLIENT_ID)
            .addHeader("X-Naver-Client-Secret", CLIENT_SECRET)
            .build()
        return@Interceptor it.proceed(request)
    }

    val client = OkHttpClient.Builder()
        .addInterceptor(headerInterceptor)
        .build()
}