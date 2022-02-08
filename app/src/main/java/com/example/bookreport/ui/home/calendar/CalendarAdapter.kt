package com.example.bookreport.ui.home.calendar

import android.graphics.*
import android.graphics.drawable.GradientDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.bookreport.databinding.ItemScheduleBinding
import com.example.bookreport.ui.home.HomeFragment
import java.text.SimpleDateFormat
import java.util.*


class CalendarAdapter(val fragment: HomeFragment) : RecyclerView.Adapter<CalendarAdapter.ViewHolder>() {

    val baseCalendar = BaseCalendar()
    var year: String = ""
    var month: String = ""
    var num_month: String = ""
    var day: Int = 0

    var oldClick = -100

    var decorate: MutableList<Boolean> = mutableListOf()

    init {
        baseCalendar.initBaseCalendar {
            refreshView(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemScheduleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return BaseCalendar.LOW_OF_CALENDAR * BaseCalendar.DAYS_OF_WEEK
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(position)
    }

    inner class ViewHolder(val binding: ItemScheduleBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int){
//            if (position % BaseCalendar.DAYS_OF_WEEK == 0) binding.tvDate.setTextColor(Color.parseColor("#ff1200"))
//            else binding.tvDate.setTextColor(Color.parseColor("#676d6e"))
            with(binding) {
                if (position < baseCalendar.prevMonthTailOffset || position >= baseCalendar.prevMonthTailOffset + baseCalendar.currentMonthMaxDate) {
                    tvDate.alpha = 0.0f
                } else {
                    tvDate.alpha = 1f
                }

                tvDate.text = baseCalendar.data[position].toString()
                clickDate.setColorFilter(Color.parseColor("#ffffff"))
                tvDate.setTextColor(Color.BLACK)
                dot.visibility = View.GONE

                val deco = dot.background as GradientDrawable
                deco.setColor(Color.parseColor("#6667AB"))

                tvDate.setOnClickListener {
                    clickDate.setColorFilter(Color.parseColor("#6667AB"))
                    tvDate.setTextColor(Color.WHITE)

                    deco.setColor(Color.WHITE) // 클릭한 날짜의 dot의 색상을 변경

                    day = baseCalendar.data[position]

                    if (oldClick != -100) notifyItemChanged(oldClick)
                    oldClick = position

                    fragment.itemListUpdate("$year-$num_month-$day") // 선택된 날짜 프래그먼트에 전달
                }

                if (position >= baseCalendar.prevMonthTailOffset && position < baseCalendar.prevMonthTailOffset + baseCalendar.currentMonthMaxDate
                        && decorate.isNotEmpty() && decorate[baseCalendar.data[position]]){
                    dot.visibility = View.VISIBLE
                }
            }
        }
    }


    fun changeToPrevMonth() {
        baseCalendar.changeToPrevMonth {
            refreshView(it)
        }
    }

    fun changeToNextMonth() {
        baseCalendar.changeToNextMonth {
            refreshView(it)
        }
    }

    private fun refreshView(calendar: Calendar) {
        notifyDataSetChanged()
        fragment.refreshCurrentMonth(calendar)

        val sdf1 = SimpleDateFormat("yyyy", Locale.KOREAN)
        val sdf2 = SimpleDateFormat("MMMM", Locale.US)
        val sdf3 = SimpleDateFormat("MM", Locale.KOREAN)

        year = sdf1.format(calendar.time)
        month = sdf2.format(calendar.time)
        num_month = sdf3.format(calendar.time)

        // 달력 월이 바뀔때 해당 월의 데이터 리스트 호출
        fragment.viewModel.calendarSelect("test", "$year-$num_month")

        // 달력의 월이 바뀔 때 목록 리스트 초기화
        fragment.itemListAdapter.dataList = mutableListOf()
        fragment.itemListAdapter.notifyDataSetChanged()
    }
}