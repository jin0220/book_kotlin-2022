package com.example.bookreport.ui.home

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.example.bookreport.R
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

    val resultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == Activity.RESULT_OK){
            with(binding) {
                title.text = it.data?.getStringExtra("title")
                author.text = it.data?.getStringExtra("author")
                publisher.text = it.data?.getStringExtra("publisher")
                ratingBar.rating = it.data?.getFloatExtra("rating", 0.0f)!!
                date.text = it.data?.getStringExtra("date")
                memo.text = it.data?.getStringExtra("memo")

                Picasso.get().load(it.data?.getStringExtra("image")).into(image)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.read_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            android.R.id.home -> {
                finish()
                return true
            }
            R.id.update -> {
                val intent2 = Intent(this, WriteActivity::class.java)
                intent2.putExtra("num", intent.getIntExtra("num", 0))
                intent2.putExtra("title", intent.getStringExtra("title"))
                intent2.putExtra("image", intent.getStringExtra("image"))
                intent2.putExtra("author", intent.getStringExtra("author"))
                intent2.putExtra("publisher", intent.getStringExtra("publisher"))
                intent2.putExtra("rating", intent.getFloatExtra("rating", 0.0f))
                intent2.putExtra("memo", intent.getStringExtra("memo"))
                intent2.putExtra("date", intent.getStringExtra("date"))
                resultLauncher.launch(intent2)
                return true
            }
            R.id.delect -> {
                readViewModel.recordDelete(intent.getIntExtra("num", -1))
                val resultIntent = Intent(this, HomeFragment::class.java)
                resultIntent.putExtra("position",  intent.getIntExtra("position", -1))
                setResult(RESULT_OK, resultIntent)
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}