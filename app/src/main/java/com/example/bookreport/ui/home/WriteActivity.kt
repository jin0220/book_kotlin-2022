package com.example.bookreport.ui.home

import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.bookreport.databinding.ActivityWriteBinding
import com.example.bookreport.ui.home.search.SearchActivity
import com.squareup.picasso.Picasso
import java.util.*

class WriteActivity : AppCompatActivity() {
    val binding by lazy { ActivityWriteBinding.inflate(layoutInflater) }
    lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

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
                binding.title.text = it.data?.getStringExtra("title")
                binding.author.text = it.data?.getStringExtra("author")
                binding.publisher.text = it.data?.getStringExtra("publisher")
                Picasso.get().load(it.data?.getStringExtra("image")).into(binding.image)
            }
        }
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

}