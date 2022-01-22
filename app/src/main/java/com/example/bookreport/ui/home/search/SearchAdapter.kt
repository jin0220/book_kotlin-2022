package com.example.bookreport.ui.home.search

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.bookreport.data.model.Book
import com.example.bookreport.databinding.ItemSearchBinding
import com.example.bookreport.ui.home.WriteActivity
import com.squareup.picasso.Picasso

class SearchAdapter: RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    var list: List<Book.Items> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(val binding: ItemSearchBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(book: Book.Items){
            with(binding) {
                Picasso.get().load(book.image).into(image)
                title.text = book.title
                author.text = book.author
                publisher.text = book.publisher

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, WriteActivity::class.java)
                    intent.putExtra("title", book.title)
                    intent.putExtra("author", book.author)
                    intent.putExtra("publisher", book.publisher)
                    intent.putExtra("image", book.image)
                    val activity = itemView.context as SearchActivity
                    activity.setResult(Activity.RESULT_OK, intent)
                    activity.finish()
                }
            }
        }
    }
}