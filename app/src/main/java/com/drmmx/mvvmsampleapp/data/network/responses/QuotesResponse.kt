package com.drmmx.mvvmsampleapp.data.network.responses

import com.drmmx.mvvmsampleapp.data.db.entities.Quote

data class QuotesResponse(
    val isSuccessful: Boolean?,
    val quotes: List<Quote>
)