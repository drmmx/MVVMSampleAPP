package com.drmmx.mvvmsampleapp.ui.home.quotes

import com.drmmx.mvvmsampleapp.R
import com.drmmx.mvvmsampleapp.data.db.entities.Quote
import com.drmmx.mvvmsampleapp.databinding.QuoteItemBinding
import com.xwray.groupie.databinding.BindableItem

class QuoteItem(
    private val quote: Quote
) : BindableItem<QuoteItemBinding>() {
    override fun getLayout() = R.layout.quote_item

    override fun bind(viewBinding: QuoteItemBinding, position: Int) {
        viewBinding.setQuote(quote)
    }
}