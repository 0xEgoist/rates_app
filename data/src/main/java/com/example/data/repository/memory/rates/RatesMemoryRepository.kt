package com.example.data.repository.memory.rates

import javax.inject.Inject

class RatesMemoryRepository @Inject constructor() {
    var baseCurrencyTicker = "EUR"
    var currentBaseRate = 100f
}