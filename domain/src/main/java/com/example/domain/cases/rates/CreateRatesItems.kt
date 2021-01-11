package com.example.domain.cases.rates

import com.example.domain.base.BaseAsyncUseCase
import com.example.domain.presentation.rates.RateItem
import com.example.domain.repository.IRatesRepository
import javax.inject.Inject

class CreateRatesItems @Inject constructor(
    private val getRates: GetRates,
    private val ratesRepository: IRatesRepository
) : BaseAsyncUseCase<Unit, List<RateItem>>() {

    override suspend fun create(arg: Unit): List<RateItem> {
        val ratesInfo = getRates(Unit)
        val currentBaseRate = ratesRepository.getCurrentBaseRate()

        return mutableListOf<RateItem>().apply {
            val baseCurrencyItem =
                createRateItem(
                    isoCode = ratesInfo.baseCurrency,
                    unitRate = 1f,
                    currentRate = currentBaseRate
                ) ?: return emptyList()
            add(baseCurrencyItem)
            ratesInfo.rates.forEach { isoCodeToRate ->
                createRateItem(
                    isoCode = isoCodeToRate.key,
                    unitRate = isoCodeToRate.value,
                    currentRate = isoCodeToRate.value * currentBaseRate
                )?.let { add(it) }
            }
        }
    }

    private fun createRateItem(
        isoCode: String,
        unitRate: Float,
        currentRate: Float
    ): RateItem? {
        val (emoji, name) = getCurrencyEmojiAndName(isoCode) ?: return null
        return RateItem(
            flagEmoji = emoji,
            ticker = isoCode,
            name = name,
            unitRate = unitRate,
            currentRate = currentRate
        )
    }
}