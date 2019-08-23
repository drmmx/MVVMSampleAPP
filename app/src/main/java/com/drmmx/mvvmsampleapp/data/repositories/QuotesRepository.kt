package com.drmmx.mvvmsampleapp.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.drmmx.mvvmsampleapp.data.db.AppDatabase
import com.drmmx.mvvmsampleapp.data.db.entities.Quote
import com.drmmx.mvvmsampleapp.data.network.MyApi
import com.drmmx.mvvmsampleapp.data.network.SafeApiRequest
import com.drmmx.mvvmsampleapp.data.preferences.PreferenceProvider
import com.drmmx.mvvmsampleapp.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val TIME_INTERVAL = 21600000

class QuotesRepository(
    private val api: MyApi,
    private val db: AppDatabase,
    private val prefs: PreferenceProvider
) : SafeApiRequest(){

    private val quotes = MutableLiveData<List<Quote>>()

    init {
        quotes.observeForever {
            saveQuotes(it)
        }
    }

    suspend fun getQuotes(): LiveData<List<Quote>> {
        return withContext(Dispatchers.IO){
            fetchQuotes()
            db.getQuoteDao().getQuotes()
        }

    }

    private suspend fun fetchQuotes() {
        val lastSavedAt = prefs.getLastSavedAt()
        if (lastSavedAt == 0L || isFetchNeeded(lastSavedAt)) {
            val response = apiRequest { api.getQuotes() }
            quotes.postValue(response.quotes)
        }
    }

    private fun isFetchNeeded(lastSavedAt: Long?): Boolean {
        return (System.currentTimeMillis() - lastSavedAt!!) > TIME_INTERVAL
    }

    private fun saveQuotes(quotes: List<Quote>) {
        Coroutines.io {
            prefs.saveLastSavedAt(System.currentTimeMillis())
            db.getQuoteDao().saveAllQuotes(quotes)
        }
    }
}