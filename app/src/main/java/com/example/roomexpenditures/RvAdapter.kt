package com.example.roomexpenditures

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomexpenditures.databinding.ItemBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class RvAdapter(val context:Context) : RecyclerView.Adapter<RvAdapter.getViewHolder>() {

    inner class getViewHolder(val binding:ItemBinding) : RecyclerView.ViewHolder(binding.root)

    val RvList = ArrayList<dateData>()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): getViewHolder {
       return getViewHolder(ItemBinding.inflate(LayoutInflater.from(context)))
    }

    override fun onBindViewHolder(viewHolder: getViewHolder, position: Int) {

        viewHolder.binding.amount.text = RvList[position].amount.toString()
        viewHolder.binding.reason.text = RvList[position].reason

        viewHolder.binding.time.text = formatedTime(RvList[position].date)

    }

    private fun formatedTime(date: Long): String {

        val timeFormat = SimpleDateFormat("h:mm a")
        return timeFormat.format(Date(date)).toString()

    }

    override fun getItemCount() = RvList.size

     @SuppressLint("NotifyDataSetChanged")
     fun updateList(list:ArrayList<dateData>){
         RvList.clear()

         if(!list.isEmpty()){
             RvList.addAll(list)
         }

         this.notifyDataSetChanged()
     }

}