package com.example.bookreport.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookreport.data.model.Calendar
import com.example.bookreport.data.model.Record
import com.example.bookreport.data.repository.DBRepository
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {
    private val repository = DBRepository()

    var response: MutableLiveData<MutableList<Record>> = MutableLiveData()

    fun recordSelect(id: String, date: String) {
        viewModelScope.launch {
            repository.recordSelect(id, date)
            response = repository.dataList
        }
    }

    var calendarResponse: MutableLiveData<MutableList<Calendar>> = MutableLiveData()
    fun calendarSelect(id: String, date: String){
        viewModelScope.launch {
            repository.calendarSelect(id, date)
            calendarResponse = repository.calendarList
        }
    }
}