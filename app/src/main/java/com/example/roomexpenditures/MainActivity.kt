package com.example.roomexpenditures

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toolbar
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomexpenditures.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Month
import java.util.*


class MainActivity : AppCompatActivity() {

    val key: String = "Date"
    private lateinit var bindingMain: ActivityMainBinding
    private lateinit var viewModel: DateViewModel
    private lateinit var viewModel1: contactViewModel

    var date: Long = 0L
    val categorylist = arrayListOf("Collect","Pay")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        viewModel1 = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory(application)).get(contactViewModel::class.java)

        setContentView(bindingMain.root)
        val lendRvAdapter = lendRvAdapter(this)

        lendRvAdapter.setOnButtonClick(object:lendRvAdapter.onButtonClick{
            override fun onCallClick(position: Int) {

                val i = Intent()
                i.action = Intent.ACTION_DIAL
                i.data = Uri.parse("tel:${lendRvAdapter.list[position].number}")

                startActivity(i)
            }

            override fun onDeleteClick(position: Int) {
                viewModel1.delete(lendRvAdapter.list[position])
            }
        })

        bindingMain.rv.layoutManager = LinearLayoutManager(this)
        bindingMain.rv.adapter = lendRvAdapter

        viewModel1.list.observe(this, androidx.lifecycle.Observer {
            lendRvAdapter.updateList(it as ArrayList<contact>)
        })

        lendRvAdapter.updateList(ArrayList<contact>())

        bindingMain.calendarView.maxDate = System.currentTimeMillis()

        viewModel =
            ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(
                DateViewModel::class.java
            )

        val today = Date(bindingMain.calendarView.date)

        val y = today.year
        val m = today.month
        val d = today.date
        val t = Date(y, m, d, 0, 0)
        date = t.time

        findSum(date)
        findMonthSum(Date(t.year,t.month,1,0,0).time, Date(t.year,t.month,Month.of(t.month+1).length((t.year % 4 == 0 && t.year %100 != 0))).time
        )
   //     Log.d("date!!!", "date1 from main = = = ${t.time}")

        bindingMain.calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->

            t.year = year - 1900
            t.month = month
            t.date = dayOfMonth
            date = t.time
            findSum(date)

            findMonthSum(Date(year-1900,month,1,0,0).time, Date(year-1900,month,Month.of(month+1).length((year % 4 == 0 && year %100 != 0))).time
            )
           // Log.d("date!!!", "date2 from main = = = ${t.time}")
        }

        bindingMain.Add.setOnClickListener {

            val i = Intent(this, EachDayActivity::class.java)
            i.putExtra(key, date)
            startActivity(i)

        }

        bindingMain.PendingLends.setOnClickListener {
            startActivity(Intent(this,BlankFragment::class.java))
        }

    }

    override fun onResume() {
        super.onResume()
        Log.d("resume!!!", "resume")
        findSum(date)

        val t = Date(date)
        findMonthSum(Date(t.year,t.month,1,0,0).time, Date(t.year,t.month,31).time

        )
    }

    fun findSum(date: Long) {

        CoroutineScope(Dispatchers.IO).launch {
            var t = viewModel.isPresent(date,date)

            if (t) {
                val s = " Rs." + viewModel.getSum(date).toString()
                bindingMain.Today.text = s
            } else {
                bindingMain.Today.text = "0 Rs."
            }

        }
    }
    fun findMonthSum(d1: Long,d2:Long) {

        CoroutineScope(Dispatchers.IO).launch {
            var t = viewModel.isPresent(d1,d2)

            if (t) {
                val s = " Rs." + viewModel.getThisMonthSum(d1,d2).toString()
                bindingMain.ThisMonth.text = s
            } else {
                bindingMain.ThisMonth.text = "0 Rs."
            }

        }
    }

}
