package com.example.bookreport.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookreport.data.model.Book
import com.example.bookreport.data.repository.BookRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class SearchViewModel: ViewModel() {
    private val repository = BookRepository()

    val myResponse : MutableLiveData<Response<Book>> = MutableLiveData()

    fun getSearchBook() {
        viewModelScope.launch {
            myResponse.value = repository.getSearchBook()
        }
    }
}