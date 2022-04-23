package com.example.roomexpenditures

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.roomexpenditures.databinding.FragmentBlankBinding


class BlankFragment:AppCompatActivity() {

    private lateinit var binding:FragmentBlankBinding
    private lateinit var viewModel: contactViewModel
    val categoryList = arrayListOf("Collect","Pay")

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory(application)).get(contactViewModel::class.java)

        binding = FragmentBlankBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.Spinner.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,categoryList)

        binding.NewTaskBtnSave.setOnClickListener {

            if(binding.name.text.isEmpty() || binding.amount.text.isEmpty()){
                Toast.makeText(this,"Please enter necessary details !",Toast.LENGTH_LONG).show()

            }else {


                val name: String = binding.name.text.toString()
                val amount: Long = binding.amount.text.toString().toLong()
                var num: Long

                if(binding.contact.text.isEmpty()){
                    num = 0L
                }else{
                    num = binding.contact.text.toString().toLong()
                }

                val type: String = binding.Spinner.selectedItem.toString()

                viewModel.insert(contact(name, num, type, amount))
                finish()
            }
        }

    }

}