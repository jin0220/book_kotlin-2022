package com.example.bookreport.ui.home

import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.bookreport.R
import com.example.bookreport.data.model.Record
import com.example.bookreport.databinding.ActivityWriteBinding
import com.example.bookreport.ui.home.search.SearchActivity
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Response
import java.util.*

class WriteActivity : AppCompatActivity() {
    val binding by lazy { ActivityWriteBinding.inflate(layoutInflater) }
//    private lateinit var binding: ActivityWriteBinding
    lateinit var writeViewModel: WriteViewModel
    lateinit var resultLauncher: ActivityResultLauncher<Intent>

    var imagePhoto: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_write)
//        binding.lifecycleOwner = this
//
        writeViewModel = ViewModelProvider(this).get(WriteViewModel::class.java)
//        binding.writeViewModel = writeViewModel

        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)


        val calendar: Calendar = Calendar.getInstance()
        var year = calendar.get(Calendar.YEAR)
        var month = calendar.get(Calendar.MONTH)
        var day = calendar.get(Calendar.DAY_OF_MONTH)

        binding.dateSet.text = "$year-${String.format("%02d", (month + 1))}-${String.format("%02d", day)}"

        binding.dateSet.setOnClickListener {
            DatePickerDialog(this, AlertDialog.THEME_HOLO_LIGHT, listener, year, month, day).show()
        }

        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if (it.resultCode == Activity.RESULT_OK){
                imagePhoto = it.data?.getStringExtra("image")!!
                binding.itemBox.visibility = View.VISIBLE

                binding.title.text = it.data?.getStringExtra("title")
                binding.author.text = it.data?.getStringExtra("author")
                binding.publisher.text = it.data?.getStringExtra("publisher")
                Picasso.get().load(imagePhoto).into(binding.image)
            }
        }

//        writeViewModel.recordResponse.observe(this,{
//            it.enqueue(object : retrofit2.Callback<Record> {
//                override fun onResponse(call: Call<Record>, response: Response<Record>) {
//                    Log.d("확인", "$response")
//                }
//
//                override fun onFailure(call: Call<Record>, t: Throwable) {
//                    Log.d("확인", "fail $call")
//                }
//            })
//        })
    }


    private var listener = DatePickerDialog.OnDateSetListener { p0, year, month, day ->
        binding.dateSet.text = "$year-${String.format("%02d", (month + 1))}-${String.format("%02d", day)}"
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun search(view: View){
        val intent = Intent(this, SearchActivity::class.java)
        resultLauncher.launch(intent)
    }

    fun store(view: View){
        with(binding) {
            // 데이터 받아오기
            val id = "test"
            val title = title.text.toString()
            val author = author.text.toString()
            val publisher = publisher.text.toString()
            val rating = rating.rating.toDouble()
            val memo = memo.text.toString()

            writeViewModel.recordInsert(id, title, imagePhoto, author,
                    publisher, rating, memo)

            finish()
        }
    }
}