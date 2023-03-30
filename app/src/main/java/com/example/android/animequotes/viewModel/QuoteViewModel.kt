package com.example.android.animequotes.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.animequotes.data.retrofit.QuoteListItem
import com.example.android.animequotes.repo.QuoteRepo
import com.example.android.animequotes.repo.Response
import kotlinx.coroutines.launch

class QuoteViewModel(val quoteRepo : QuoteRepo) : ViewModel() {

    init {
        viewModelScope.launch {
            quoteRepo.getQuotes("erwin")
        }
    }

    val quotes : LiveData<Response<List<QuoteListItem>>>
    get() = quoteRepo.liveQuote

    fun getQuotes(name:String){
        viewModelScope.launch{
            quoteRepo.getQuotes(name)
        }
    }

}