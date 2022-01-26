package com.example.bookreport.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookreport.data.model.Record
import com.example.bookreport.data.repository.DBRepository
import kotlinx.coroutines.launch
import retrofit2.Call

class WriteViewModel: ViewModel() {
    private val repository = DBRepository()

    val recordResponse: MutableLiveData<Call<Record>> = MutableLiveData()

    fun recordInsert(record: Record) {
        viewModelScope.launch {
            recordResponse.value = repository.recordInsert(record)
        }
    }
}