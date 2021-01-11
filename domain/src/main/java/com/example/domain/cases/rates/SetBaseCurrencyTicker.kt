package com.example.domain.cases.rates

import com.example.domain.base.BaseUseCase
import com.example.domain.repository.IRatesRepository
import javax.inject.Inject

class SetBaseCurrencyTicker @Inject constructor(
    private val ratesRepository: IRatesRepository
): BaseUseCase<String, Unit>() {

    override fun create(arg: String) {
        ratesRepository.setBaseCurrencyTicker(arg)
    }
}