package com.example.bookreport.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bookreport.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {
    val binding by lazy { ActivitySearchBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}