package com.example.domain.repository.models.rates

data class RatesResult(
    val baseCurrency: String,
    val rates: Map<String, Float>
)