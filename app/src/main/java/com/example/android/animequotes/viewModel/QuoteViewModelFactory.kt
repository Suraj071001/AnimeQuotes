package com.example.android.animequotes.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.animequotes.repo.QuoteRepo

class QuoteViewModelFactory(private val quoteRepo: QuoteRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return QuoteViewModel(quoteRepo) as T
    }
}