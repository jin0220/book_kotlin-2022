package com.example.bookreport.data.repository

import android.util.Log
import com.example.bookreport.data.api.RetrofitClient
import com.example.bookreport.data.model.Book
import retrofit2.Response

class BookRepository{
    suspend fun getSearchBook(string: String) : Response<Book> {
        return RetrofitClient.getApiService().getSearchBook("book.json", string)
    }
}