package com.example.android.animequotes

import android.app.Application
import android.content.ContentValues.TAG
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Adapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.android.animequotes.adapter.QuoteAdapter
import com.example.android.animequotes.data.retrofit.QuoteApi
import com.example.android.animequotes.data.retrofit.QuoteRetrofit
import com.example.android.animequotes.data.room.AnimeDatabase
import com.example.android.animequotes.databinding.ActivityMainBinding
import com.example.android.animequotes.repo.QuoteRepo
import com.example.android.animequotes.repo.Response
import com.example.android.animequotes.viewModel.QuoteViewModel
import com.example.android.animequotes.viewModel.QuoteViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var quoteViewModel : QuoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);

        val quoteRepo = (application as QuoteApplication).repo

        quoteViewModel = ViewModelProvider(this,QuoteViewModelFactory(quoteRepo))[QuoteViewModel::class.java]

        quoteViewModel.quotes.observe(this){
            when(it){

                is Response.Loading -> {
                    binding.pbQuote.visibility = View.VISIBLE
                }
                is Response.Success -> {
                    binding.pbQuote.visibility = View.INVISIBLE
                    binding.vpQuotes.adapter = it.data?.let { listOfQuotes -> QuoteAdapter(listOfQuotes) }
                }
                is Response.Error -> {
                    binding.pbQuote.visibility = View.INVISIBLE
                    Toast.makeText(this@MainActivity,"Check your internet connection",Toast.LENGTH_SHORT).show()
            }
            }

        }

        binding.fabNext.setOnClickListener{
            quoteViewModel.getQuotes(binding.etCharacter.text.toString())
        }

        binding.ivBack.setOnClickListener{
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Exit")
            builder.setMessage("Are you sure you want to exit?")

            builder.setPositiveButton("yes") { _, _ ->
                finish()
            }

            builder.setNegativeButton("no"){_,_->}
            builder.show()
        }
    }
}