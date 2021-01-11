package com.example.domain.cases.rates

import com.example.domain.base.BaseAsyncUseCase
import com.example.domain.presentation.rates.RateItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

const val DELAY_MILLISECONDS = 1000L

class GetRatesFlow @Inject constructor(
    private val createRatesItems: CreateRatesItems
) : BaseAsyncUseCase<Unit, Flow<List<RateItem>>>() {

    override suspend fun create(arg: Unit): Flow<List<RateItem>> {
        return flow {
            while (true) {
                emit(
                    createRatesItems(Unit)
                )
                delay(DELAY_MILLISECONDS)
            }
        }
    }
}