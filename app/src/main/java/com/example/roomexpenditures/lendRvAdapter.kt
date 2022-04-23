package com.example.roomexpenditures

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.loader.content.Loader
import androidx.recyclerview.widget.RecyclerView
import com.example.roomexpenditures.databinding.LendItemBinding

class lendRvAdapter(val context: Context): RecyclerView.Adapter<lendRvAdapter.getViewHold>() {

    lateinit var onButtonClick1: onButtonClick
    var list = ArrayList<contact>()

    fun setOnButtonClick(onButtonClick: onButtonClick){
        onButtonClick1 = onButtonClick
    }

    interface onButtonClick{

        fun onCallClick(position: Int)

        fun onDeleteClick(position: Int)

    }

    inner class getViewHold(val binding:LendItemBinding,val onButtonClick: onButtonClick):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): getViewHold {
         return getViewHold(LendItemBinding.inflate(LayoutInflater.from(context)),onButtonClick1)
    }

    override fun onBindViewHolder(holder: getViewHold, position: Int) {

        holder.binding.name.text = list[position].name
        holder.binding.mobileNo.text = list[position].number.toString()
        holder.binding.type.text = list[position].type
        holder.binding.lend.text = list[position].amount.toString()

        holder.binding.call.setOnClickListener {
            holder.onButtonClick.onCallClick(position)
        }

        holder.binding.delete.setOnClickListener {
            holder.onButtonClick.onDeleteClick(position)
        }

    }

    override fun getItemCount() = list.size

    fun updateList( list1:ArrayList<contact>){
        list.clear()

        if(list1.isNotEmpty()){
            list.addAll(list1)
        }

        this.notifyDataSetChanged()
    }

}
