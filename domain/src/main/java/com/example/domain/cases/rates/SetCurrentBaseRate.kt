package com.example.domain.cases.rates

import com.example.domain.base.BaseUseCase
import com.example.domain.repository.IRatesRepository
import javax.inject.Inject

class SetCurrentBaseRate @Inject constructor(
    private val ratesRepository: IRatesRepository
): BaseUseCase<Float, Unit>() {

    override fun create(arg: Float) {
        ratesRepository.setCurrentBaseRate(arg)
    }
}