package com.example.bookreport.ui.home

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.bookreport.data.model.Record
import com.example.bookreport.databinding.ItemListBinding
import com.squareup.picasso.Picasso

class ItemListAdapter: RecyclerView.Adapter<ItemListAdapter.ViewHolder>(){
    var dataList: MutableList<Record> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size

    inner class ViewHolder(val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: Record){
            with(binding) {
                title.text = item.title
                Picasso.get().load(item.image).into(image)

                itemBox.setOnClickListener {
                    val intent = Intent(itemBox.context, ReadActivity::class.java)
                    intent.putExtra("title", item.title)
                    intent.putExtra("image", item.image)
                    intent.putExtra("author", item.author)
                    intent.putExtra("publisher", item.publisher)
                    intent.putExtra("rating", item.rating)
                    intent.putExtra("memo", item.memo)
                    intent.putExtra("date", item.date)
                    ContextCompat.startActivity(itemBox.context, intent, null)
                }
            }
        }
    }

}