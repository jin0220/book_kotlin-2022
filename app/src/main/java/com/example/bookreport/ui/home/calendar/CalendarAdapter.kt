package com.example.bookreport.ui.home.calendar

import android.graphics.*
import android.view.LayoutInflater
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

    var oldClick = -1

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

                tvDate.setOnClickListener {
                    Toast.makeText(dateBox.context, "$year $month ${baseCalendar.data[position]}", Toast.LENGTH_SHORT).show()
                    clickDate.setColorFilter(Color.parseColor("#6667AB"))
                    tvDate.setTextColor(Color.WHITE)

                    if (oldClick != -1) notifyItemChanged(oldClick) // 오류!! dot 있는 부분 클릭 후 다른 곳을 클릭하면 그 부분에 dot이 생김
                    oldClick = position
                }

//            binding.dot.addView(PointView(binding.dot.context))
                if (oldClick == -1 && year == "2022" && month == "January" && baseCalendar.data[position] == 7) {
                    dot(binding) // 오류!! dot 있는 부분 클릭 후 다른 곳을 클릭하면 그 부분에 dot이 생김
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

        year = sdf1.format(calendar.time)
        month = sdf2.format(calendar.time)
    }

    fun dot(binding: ItemScheduleBinding){
        val bitmap = Bitmap.createBitmap(50, 50, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        canvas.drawColor(Color.TRANSPARENT)
        binding.dot.setImageBitmap(bitmap)

        val paint = Paint()
        paint.setColor(Color.parseColor("#6667AB"))
        paint.setStrokeWidth(5f)

        // 도화지에 좌표로 표시하기
        val rect = RectF()
        rect.set(22f,14f,28f,20f)

        // 원 그리기
        canvas.drawArc(rect, 0f, 360f, true, paint)
    }
}