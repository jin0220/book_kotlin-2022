package com.example.bookreport.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookreport.data.repository.DBRepository
import kotlinx.coroutines.launch

class ReadViewModel: ViewModel() {
    private val repository = DBRepository()

    fun recordDelete(num: Int) {
        viewModelScope.launch {
            repository.recordDelete(num)
        }
    }
}