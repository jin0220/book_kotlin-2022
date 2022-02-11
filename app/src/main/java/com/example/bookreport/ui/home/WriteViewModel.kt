package com.example.bookreport.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookreport.data.model.PostModel
import com.example.bookreport.data.model.Record
import com.example.bookreport.data.repository.DBRepository
import kotlinx.coroutines.launch
import retrofit2.Call

class WriteViewModel: ViewModel() {
    private val repository = DBRepository()

    val recordResponse: MutableLiveData<Call<Record>> = MutableLiveData()

    fun recordInsert(id: String, title: String, imagePhoto: String, author: String,
                     publisher: String, rating: Float, memo: String) {
        viewModelScope.launch {
            repository.recordInsert(id, title, imagePhoto, author, publisher, rating, memo)
        }
    }

    fun recordUpdate(num: Int, title: String, imagePhoto: String, author: String,
                       publisher: String, rating: Float, memo: String, date: String){
        viewModelScope.launch {
            repository.recordUpdate(num, title, imagePhoto, author, publisher, rating, memo, date)
        }
    }
}