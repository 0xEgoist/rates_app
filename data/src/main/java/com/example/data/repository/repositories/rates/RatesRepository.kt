package com.example.data.repository.repositories.rates

import com.example.data.repository.converters.toDomainObj
import com.example.data.repository.memory.rates.RatesMemoryRepository
import com.example.data.repository.remote.rates.IRatesApi
import com.example.domain.common.Result
import com.example.domain.repository.IRatesRepository
import com.example.domain.repository.models.rates.RatesResult
import javax.inject.Inject

class RatesRepository @Inject constructor(
    private val ratesApi: IRatesApi,
    private val memoryRepository: RatesMemoryRepository
) : IRatesRepository {

    override suspend fun getRatesAsync(currency: String): Result<RatesResult> {
        val result =
            ratesApi.getRatesResultAsync(currency)
        if (result.isSuccessful) {
            return Result.Success(result.body().toDomainObj())
        }
        return Result.Error(result.code(), result.message())
    }

    override fun setBaseCurrencyTicker(ticker: String) {
        memoryRepository.baseCurrencyTicker = ticker
    }

    override fun setCurrentBaseRate(rate: Float) {
        memoryRepository.currentBaseRate = rate
    }

    override fun getBaseCurrencyTicker() = memoryRepository.baseCurrencyTicker

    override fun getCurrentBaseRate() = memoryRepository.currentBaseRate
}
