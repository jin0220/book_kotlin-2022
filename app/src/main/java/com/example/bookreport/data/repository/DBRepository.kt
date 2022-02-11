package com.example.bookreport.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.bookreport.data.api.DBRetrofitClient
import com.example.bookreport.data.model.Calendar
import com.example.bookreport.data.model.PostModel
import com.example.bookreport.data.model.Record
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DBRepository {
    fun recordInsert(id: String, title: String, imagePhoto: String, author: String,
                     publisher: String, rating: Float, memo: String) {
        val call = DBRetrofitClient.api.recordInsert(id, title, imagePhoto, author,
                publisher, rating, memo)

        call.enqueue(object : Callback<Record> {
            override fun onResponse(call: Call<Record>, response: Response<Record>) {
                if(response.isSuccessful){
                    // 정상적인 통신
                    Log.d("확인", "응답 성공 -> ${response.body()}")
                }
                else{
                    // 통신 실패 (응답코드 3xx, 4xx 등)
                }
            }

            override fun onFailure(call: Call<Record>, t: Throwable) {
                // 통신 실패 (인터넷 끊김, 예외발생 등 시스템적인 이유)
                t.printStackTrace()
            }

        })
    }

    var dataList: MutableLiveData<MutableList<Record>> = MutableLiveData()
    fun recordSelect(id: String, date: String){
        val call = DBRetrofitClient.api.recordSelect(id, date)

        call.enqueue(object : Callback<MutableList<Record>>{
            override fun onResponse(call: Call<MutableList<Record>>, response: Response<MutableList<Record>>) {
                if (response.isSuccessful){
                    Log.d("확인", "recordSelect 응답 성공 -> ${response.body()}")
                    dataList.value = response.body()!!
                }
                else{
                    Log.d("확인", "recordSelect 응답 실패")
                }
            }

            override fun onFailure(call: Call<MutableList<Record>>, t: Throwable) {
                t.printStackTrace()
                Log.d("확인", "recordSelect 통신 실패")
            }

        })
    }

    var calendarList: MutableLiveData<MutableList<Calendar>> = MutableLiveData()
    fun calendarSelect(id: String, date: String){
        val call = DBRetrofitClient.api.calendarSelect(id, date)

        call.enqueue(object : Callback<MutableList<Calendar>>{
            override fun onResponse(call: Call<MutableList<Calendar>>, response: Response<MutableList<Calendar>>) {
                if (response.isSuccessful){
                    Log.d("확인", "calendarSelect 응답 성공 -> ${response.body()}")
                    calendarList.value = response.body()!!
                }
                else{
                    Log.d("확인", "calendarSelect 응답 실패")
                }
            }

            override fun onFailure(call: Call<MutableList<Calendar>>, t: Throwable) {
                t.printStackTrace()
                Log.d("확인", "calendarSelect 통신 실패")
            }

        })
    }

    fun recordDelete(num: Int){
        val call = DBRetrofitClient.api.recordDelete(num)

        call.enqueue(object : Callback<MutableList<PostModel>>{
            override fun onResponse(call: Call<MutableList<PostModel>>, response: Response<MutableList<PostModel>>) {
                if (response.isSuccessful){
                    Log.d("확인", "recordDelete 응답 성공 -> ${response.body()}")
                }
                else{
                    Log.d("확인", "recordDelete 응답 실패")
                }
            }

            override fun onFailure(call: Call<MutableList<PostModel>>, t: Throwable) {
                t.printStackTrace()
                Log.d("확인", "recordDelete 통신 실패")
            }

        })
    }

    fun recordUpdate(num: Int, title: String, imagePhoto: String, author: String,
                     publisher: String, rating: Float, memo: String, date: String ){
        val call = DBRetrofitClient.api.recordUpdate(num, title, imagePhoto, author, publisher, rating, memo, date)

        call.enqueue(object : Callback<MutableList<PostModel>>{
            override fun onResponse(call: Call<MutableList<PostModel>>, response: Response<MutableList<PostModel>>) {
                if (response.isSuccessful){
                    Log.d("확인", "응답 성공 -> ${response.body()}")
                }
                else{
                    Log.d("확인", "응답 실패")
                }
            }

            override fun onFailure(call: Call<MutableList<PostModel>>, t: Throwable) {
                t.printStackTrace()
                Log.d("확인", "통신 실패")
            }

        })
    }
}