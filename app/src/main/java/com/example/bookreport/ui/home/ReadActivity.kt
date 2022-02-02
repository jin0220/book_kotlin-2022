package com.example.bookreport.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.example.bookreport.databinding.ActivityReadBinding
import com.squareup.picasso.Picasso

class ReadActivity : AppCompatActivity() {
    val binding by lazy { ActivityReadBinding.inflate(layoutInflater) }
    lateinit var readViewModel: ReadViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        readViewModel = ViewModelProvider(this).get(ReadViewModel::class.java)

        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        with(binding){
            title.text = intent.getStringExtra("title")
            author.text = intent.getStringExtra("author")
            publisher.text = intent.getStringExtra("publisher")
            ratingBar.rating = intent.getFloatExtra("rating",0.0f)
            date.text = intent.getStringExtra("date")
            memo.text = intent.getStringExtra("memo")

            Picasso.get().load(intent.getStringExtra("image")).into(image)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}