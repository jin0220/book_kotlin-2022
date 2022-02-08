package com.example.bookreport.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.bookreport.data.model.Record
import com.example.bookreport.databinding.ItemListBinding
import com.squareup.picasso.Picasso

class ItemListAdapter: RecyclerView.Adapter<ItemListAdapter.ViewHolder>(){
    var dataList: MutableList<Record> = mutableListOf()

    interface OnItemClickListener {
        fun onItemClick(v: View?, position: Int, data: Record) //아이템을 클릭할 때 데이터도 함께 넘기기 위해 plant3변수도 넣어줌.
    }

    // 리스너 객체 참조를 저장하는 변수
    private var mListener: OnItemClickListener? = null

    // OnItemClickListener 리스너 객체 참조를 어댑터에 전달하는 메서드
    fun setOnItemClickListener(listener: OnItemClickListener?) {
        mListener = listener
    }

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

//                itemBox.setOnClickListener {
//                    val intent = Intent(itemBox.context, ReadActivity::class.java)
//                    intent.putExtra("num", item.num)
//                    intent.putExtra("title", item.title)
//                    intent.putExtra("image", item.image)
//                    intent.putExtra("author", item.author)
//                    intent.putExtra("publisher", item.publisher)
//                    intent.putExtra("rating", item.rating)
//                    intent.putExtra("memo", item.memo)
//                    intent.putExtra("date", item.date)
//                    ContextCompat.startActivity(itemBox.context, intent, null)
//                }

                itemView.setOnClickListener { v ->
                    val position = adapterPosition //클릭한 아이템 위치
                    if (position != RecyclerView.NO_POSITION) { //뷰홀더가 참조하는 아이템이 어댑터에서 삭제되면 getAdapterPosition() 메서드는 NO_POSITION을 리턴하기 때문
                        // 리스너 객체의 메서드 호출.
                        if (mListener != null) {
                            mListener!!.onItemClick(v, position, item)
                        }
                    }
                }
            }
        }
    }

}