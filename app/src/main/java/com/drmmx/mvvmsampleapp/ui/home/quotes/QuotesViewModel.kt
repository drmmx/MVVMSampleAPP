package com.drmmx.mvvmsampleapp.ui.home.quotes

import androidx.lifecycle.ViewModel
import com.drmmx.mvvmsampleapp.data.repositories.QuotesRepository
import com.drmmx.mvvmsampleapp.util.lazyDeferred

class QuotesViewModel(
    repository: QuotesRepository
) : ViewModel() {

    val quotes by lazyDeferred {
        repository.getQuotes()
    }

}
