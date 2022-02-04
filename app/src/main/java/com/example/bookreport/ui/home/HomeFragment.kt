package com.example.bookreport.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookreport.databinding.FragmentHomeBinding
import com.example.bookreport.ui.home.calendar.BaseCalendar
import com.example.bookreport.ui.home.calendar.CalendarAdapter
import java.text.SimpleDateFormat
import java.util.*


class HomeFragment : Fragment(), HomeUpdate{

    private var mBinding: FragmentHomeBinding? = null

    lateinit var scheduleRecyclerViewAdapter: CalendarAdapter
    lateinit var itemListAdapter: ItemListAdapter
    lateinit var viewModel: HomeViewModel

    private val SWIPE_DISTANCE_THRESHOLD = 100
    private val SWIPE_VELOCITY_THRESHOLD = 100

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        mBinding = binding

        initView()

        // 달력 스와이프 이벤트
        var detector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
            override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
                val distanceX = e2.x - e1.x
                val distanceY = e2.y - e1.y
                if (Math.abs(distanceX) > Math.abs(distanceY)
                        && Math.abs(distanceX) > SWIPE_DISTANCE_THRESHOLD
                        && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (distanceX > 0) scheduleRecyclerViewAdapter.changeToPrevMonth()
                    else scheduleRecyclerViewAdapter.changeToNextMonth()
                    return true
                }
                return false
            }
        })

        mBinding?.rvSchedule?.setOnTouchListener { view, motionEvent ->
            detector.onTouchEvent(motionEvent)
        }

        mBinding?.write?.setOnClickListener {
            val intent = Intent(context, WriteActivity::class.java)
            startActivity(intent)
        }

        return mBinding?.root
    }

    fun initView() {
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        // 아이템 리스트
        itemListAdapter = ItemListAdapter()
        mBinding?.itemList?.layoutManager = object : LinearLayoutManager(context){
            // 리사이클러뷰 스크롤 막기
            override fun canScrollHorizontally(): Boolean = false
            override fun canScrollVertically(): Boolean = false
        }
        mBinding?.itemList?.adapter = itemListAdapter

        // 달력
        scheduleRecyclerViewAdapter = CalendarAdapter(this)

        mBinding?.rvSchedule?.layoutManager = GridLayoutManager(context, BaseCalendar.DAYS_OF_WEEK)
        mBinding?.rvSchedule?.adapter = scheduleRecyclerViewAdapter

        mBinding?.tvPrevMonth?.setOnClickListener {
            scheduleRecyclerViewAdapter.changeToPrevMonth()
        }

        mBinding?.tvNextMonth?.setOnClickListener {
            scheduleRecyclerViewAdapter.changeToNextMonth()
        }


        // 달력에 표시하기 위해 db에 저장된 날짜 정보를 처리
        viewModel.calendarResponse.observe(viewLifecycleOwner, {
            var check = MutableList(32) { index -> false }

            if(it.isNotEmpty()) {
                for (i in it.indices) {
                    check[it[i].date.split("-")[2].toInt()] = true
                }
            }

            scheduleRecyclerViewAdapter.decorate = check
            scheduleRecyclerViewAdapter.notifyDataSetChanged()
        })

    }

    fun refreshCurrentMonth(calendar: Calendar) {
        val sdf1 = SimpleDateFormat("yyyy", Locale.KOREAN)
        val sdf2 = SimpleDateFormat("MMMM", Locale.US)

        mBinding?.tvCurrentYear?.text = sdf1.format(calendar.time)
        mBinding?.tvCurrentMonth?.text = sdf2.format(calendar.time)
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }

    // interface 구현, 날짜 클릭 시 해당 날짜에 저장된 데이터 가져옴
    override fun itemListUpdate(date: String) {
        viewModel.recordSelect("test", date)

        viewModel.response.observe(this,{
            itemListAdapter.dataList = it
            itemListAdapter.notifyDataSetChanged()
        })

    }

}
