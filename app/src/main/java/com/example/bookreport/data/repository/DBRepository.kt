package com.example.bookreport.data.repository

import android.util.Log
import com.example.bookreport.data.api.DBRetrofitClient
import com.example.bookreport.data.model.PostModel
import com.example.bookreport.data.model.Record
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DBRepository {
    fun recordInsert(id: String, title: String, imagePhoto: String, author: String,
                     publisher: String, rating: Double, memo: String) {
        val call = DBRetrofitClient.recordInsert().recordInsert(id, title, imagePhoto, author,
                publisher, rating, memo)

        call.enqueue(object : Callback<Record> {
            override fun onResponse(call: Call<Record>, response: Response<Record>) {
                Log.d("확인", "${response.body()}")
            }

            override fun onFailure(call: Call<Record>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }
}