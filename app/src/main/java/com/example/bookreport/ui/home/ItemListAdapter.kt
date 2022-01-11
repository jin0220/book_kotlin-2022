package com.example.bookreport.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.bookreport.databinding.ItemListBinding

class ItemListAdapter: RecyclerView.Adapter<ItemListAdapter.ViewHolder>(){
    var dataList: MutableList<String> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(position)
    }

    override fun getItemCount(): Int = dataList.size

    inner class ViewHolder(val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int){
            with(binding) {
                itemBox.setOnClickListener {
                    val intent = Intent(itemBox.context, ReadActivity::class.java)
                    ContextCompat.startActivity(itemBox.context, intent, null)
                }
            }
        }
    }

    fun addItem(list: MutableList<String>){
        this.dataList = list
        notifyDataSetChanged()
    }

}