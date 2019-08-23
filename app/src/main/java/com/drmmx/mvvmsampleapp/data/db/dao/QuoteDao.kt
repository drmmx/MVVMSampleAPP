package com.drmmx.mvvmsampleapp.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.drmmx.mvvmsampleapp.data.db.entities.Quote

@Dao
interface QuoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllQuotes(quotes: List<Quote>)

    @Query("SELECT * FROM quote")
    fun getQuotes(): LiveData<List<Quote>>
}