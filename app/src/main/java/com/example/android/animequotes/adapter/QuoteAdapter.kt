package com.example.android.animequotes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.animequotes.data.retrofit.QuoteListItem
import com.example.android.animequotes.databinding.QuoteItemBinding

class QuoteAdapter(val list : List<QuoteListItem>) : RecyclerView.Adapter<QuoteAdapter.MyViewHolder>() {

    lateinit var binding: QuoteItemBinding

    class MyViewHolder(itemView : QuoteItemBinding) : RecyclerView.ViewHolder(itemView.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = QuoteItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        binding.quoteListItem = list[position]
    }

    override fun getItemCount(): Int {
        return list.size
    }
}