package com.example.bookreport.data.repository

import com.example.bookreport.data.api.DBRetrofitClient
import com.example.bookreport.data.model.Record
import retrofit2.Call

class DBRepository {
    suspend fun recordInsert(record: Record): Call<Record> {
        return DBRetrofitClient.recordInsert().recordInsert(record)
    }
}