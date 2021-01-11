package com.example.domain.presentation.rates

import com.example.domain.presentation.base.IAdapterItem

// icon, ticker, name, rate

data class RateItem(
    val flagEmoji: String,
    val ticker: String,
    val name: String,
    val unitRate: Float,
    val currentRate: Float
) : IAdapterItem