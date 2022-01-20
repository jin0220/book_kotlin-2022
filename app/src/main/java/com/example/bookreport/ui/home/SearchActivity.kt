package com.example.bookreport.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookreport.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {
    val binding by lazy { ActivitySearchBinding.inflate(layoutInflater) }
    lateinit var searchViewModel: SearchViewModel
    lateinit var adapter: SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)

        adapter = SearchAdapter()
        binding.searchResult.adapter = adapter
        binding.searchResult.layoutManager = LinearLayoutManager(this)

        searchViewModel.myResponse.observe(this, Observer {
            if(it.isSuccessful){
//                Log.d("확인", it.body().toString())
                Log.d("확인", it.body().toString())
                Log.d("확인", it.body()?.items?.get(0)?.title.toString())
                Log.d("확인", it.body()?.items.toString())
            }
            else{
                Log.d("확인", "실패")
            }
        })

        binding.searchBtn.setOnClickListener {
            if(binding.editText.text.isNotEmpty()){
                searchViewModel.getSearchBook()
            }
        }

    }
}