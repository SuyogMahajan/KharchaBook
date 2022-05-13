package com.example.roomexpenditures

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomexpenditures.databinding.ActivityEachDayBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class EachDayActivity: AppCompatActivity() {

    private lateinit var binding:ActivityEachDayBinding
    private lateinit var viewModel: DateViewModel
    val key:String = "Date"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(DateViewModel::class.java)

        val defaultDate = getDefaultDate()
        val date = intent.getLongExtra(key,defaultDate.time)

        binding = ActivityEachDayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rv.layoutManager = LinearLayoutManager(this)

        val rvAdapter = RvAdapter(this,viewModel)
        binding.rv.adapter = rvAdapter

        viewModel.getData(date).observe(this, Observer{

            rvAdapter.updateList(it as ArrayList<dateData>)

        })

        binding.save.setOnClickListener {
            saveData(date)
        }

        rvAdapter.setOnDeleteClicked(object:RvAdapter.onDeleteClicked{
            override fun onClickDelete(position: Int) {
                viewModel.deleteData(rvAdapter.RvList[position])
            }


        })

    }



    private fun getDefaultDate(): Date {

        val simpleDateFormat = SimpleDateFormat("dd mm yyyy")
        return simpleDateFormat.parse(simpleDateFormat.format(Date()))

    }

    private fun saveData(date:Long) {
        if(binding.reasonInput.text.isEmpty() || binding.amountInput.text.isEmpty()){
            Toast.makeText(this,"Please Enter Details",Toast.LENGTH_SHORT).show()
        }else{

            val reason = binding.reasonInput.text.toString()
            val amount = binding.amountInput.text.toString()
            val time  = calTime();

            viewModel.insert(dateData(date,reason,amount.toLong(),time))

            binding.reasonInput.text.clear()
            binding.amountInput.text.clear()
        }
    }

    private fun calTime(): String {
        val time = System.currentTimeMillis();
        return formatedTime(time);
    }

    private fun formatedTime(t:Long):String{
        val sdf = SimpleDateFormat("h:mm a")
        return sdf.format(Date(t)).toString()
    }
}