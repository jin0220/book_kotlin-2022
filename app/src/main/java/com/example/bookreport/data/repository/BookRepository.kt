package com.example.bookreport.data.repository

import android.util.Log
import com.example.bookreport.data.api.RetrofitClient
import com.example.bookreport.data.model.Book
import retrofit2.Response

class BookRepository{
    suspend fun getSearchBook() : Response<Book> {
        return RetrofitClient.getApiService().getSearchBook("book.json","달러구트 꿈 백화점")
    }
}