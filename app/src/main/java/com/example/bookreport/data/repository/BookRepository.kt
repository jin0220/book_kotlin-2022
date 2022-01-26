package com.example.bookreport.data.repository

import com.example.bookreport.data.api.ApiRetrofitClient
import com.example.bookreport.data.model.Book
import retrofit2.Response

class BookRepository{
    suspend fun getSearchBook(string: String) : Response<Book> {
        return ApiRetrofitClient.getApiService().getSearchBook("book.json", string)
    }
}