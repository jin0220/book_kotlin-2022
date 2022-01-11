package com.example.bookreport.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bookreport.databinding.ActivityReadBinding

class ReadActivity : AppCompatActivity() {
    val binding by lazy { ActivityReadBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.hide()
    }
}