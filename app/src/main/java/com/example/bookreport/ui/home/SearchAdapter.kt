package com.example.bookreport.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bookreport.data.model.Book
import com.example.bookreport.databinding.ItemListBinding

class SearchAdapter: RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    var list: MutableList<Book> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind()
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(){
            Log.d("확인", list.toString())
        }
    }
}