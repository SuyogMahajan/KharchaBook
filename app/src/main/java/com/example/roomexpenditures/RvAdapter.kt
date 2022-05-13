package com.example.roomexpenditures

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.roomexpenditures.databinding.ItemBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class RvAdapter(val context:Context,val viewModel: DateViewModel) : RecyclerView.Adapter<RvAdapter.getViewHolder>() {

    inner class getViewHolder(val binding:ItemBinding,val onDeleteClicked: onDeleteClicked) : RecyclerView.ViewHolder(binding.root)

    lateinit var ondeleteClicked: onDeleteClicked
    val RvList = ArrayList<dateData>()


    fun setOnDeleteClicked(onDeleteClicked: onDeleteClicked){
        ondeleteClicked = onDeleteClicked
    }

    interface onDeleteClicked{
        fun onClickDelete(position:Int)

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): getViewHolder {
       return getViewHolder(ItemBinding.inflate(LayoutInflater.from(p0.context)),ondeleteClicked)
    }

    override fun onBindViewHolder(viewHolder: getViewHolder, position: Int) {

        viewHolder.binding.amount.text = RvList[position].amount.toString()
        viewHolder.binding.reason.text = RvList[position].reason
        viewHolder.binding.time.text = RvList[position].time

        viewHolder.binding.delete.setOnClickListener{
           viewHolder.onDeleteClicked.onClickDelete(position)
        }

        viewHolder.binding.item.setOnClickListener {
            viewHolder.binding.delete.apply {
                if(visibility == View.VISIBLE){
                    visibility = View.GONE
                }else{
                    visibility = View.VISIBLE
                }

            }
        }
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