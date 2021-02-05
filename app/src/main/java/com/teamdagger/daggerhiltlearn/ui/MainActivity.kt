package com.teamdagger.daggerhiltlearn.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.teamdagger.daggerhiltlearn.R
import com.teamdagger.daggerhiltlearn.databinding.ActivityMainBinding
import com.teamdagger.daggerhiltlearn.model.Blog
import com.teamdagger.daggerhiltlearn.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import java.lang.StringBuilder
import java.text.SimpleDateFormat
import java.util.*
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private lateinit var binding :ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var date = "2020-12-31"
        var dtf = SimpleDateFormat("yyyy-MM-dd")
        var c = Calendar.getInstance()
        var c2 = Calendar.getInstance()
        c.time = dtf.parse(date)
        c2.time = dtf.parse(date)
        c.add(Calendar.DATE,1)
        c2.add(Calendar.DATE,2)
        date = dtf.format(c.time)
        Log.w("610",date)
        var newDtf = SimpleDateFormat("dd")
        Log.w("610",newDtf.format(c.time))
        Log.w("Is C before C2 ?",c2.time.before(c.time).toString())



        subscribe()
        viewModel.setStateEvent(MainStateEvent.GetBlogEvents)
    }

    private fun subscribe(){
        viewModel.dataState.observe(this,  {
            when(it){
                is DataState.Success<List<Blog>>->{
                    displayData(it.data)
                }
                is DataState.Error ->{
                    binding.tvDisplay.text = it.exception.message.toString()
                }
                is DataState.Loading ->{
                    Toast.makeText(this@MainActivity,"Loading...",Toast.LENGTH_LONG).show()
                }
            }
        })
    }




    private fun displayData(list : List<Blog>){

        var sb = StringBuilder()
        for(blog in list){
            sb.append(blog.title+"\n")
        }
        binding.tvDisplay.text = sb.toString()
    }
}