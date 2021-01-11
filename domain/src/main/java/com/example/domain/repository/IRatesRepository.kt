package com.example.domain.repository

import com.example.domain.common.Result
import com.example.domain.repository.models.rates.RatesResult

interface IRatesRepository {
    suspend fun getRatesAsync(currency: String): Result<RatesResult>
    fun setBaseCurrencyTicker(ticker: String)
    fun setCurrentBaseRate(rate: Float)
    fun getBaseCurrencyTicker(): String
    fun getCurrentBaseRate(): Float
}