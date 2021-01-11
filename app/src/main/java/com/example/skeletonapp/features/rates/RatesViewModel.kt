package com.example.skeletonapp.features.rates

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.cases.rates.GetRatesFlow
import com.example.domain.cases.rates.SetBaseCurrencyTicker
import com.example.domain.cases.rates.SetCurrentBaseRate
import com.example.domain.presentation.rates.RateItem
import com.example.skeletonapp.core.presenter.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.launch
import javax.inject.Inject

class RatesViewModel @Inject constructor(
    private val getRatesFlow: GetRatesFlow,
    private val setBaseCurrencyTicker: SetBaseCurrencyTicker,
    private val setCurrentBaseRate: SetCurrentBaseRate
) : BaseViewModel() {

    private var rateItems = emptyList<RateItem>()
        set(value) {
            field = value
            rates.postValue(field)
        }

    val rates = MutableLiveData<List<RateItem>>()
    val isError = MutableLiveData<Boolean>()
    val isLoading = MutableLiveData<Boolean>()

    init {
        getRates()
    }

    fun onRateClicked(fromPosition: Int) {
        val itemToMove = rateItems.getOrNull(fromPosition) ?: return
        setBaseCurrencyTicker(itemToMove.ticker)
        val changedItems = rateItems.sortedBy { it.ticker }.toMutableList().apply {
            remove(itemToMove)
            add(0, itemToMove)
            setCurrentBaseRate(firstOrNull()?.currentRate ?: return)
        }
        rateItems = changedItems
    }

    fun onRateChanged(rate: String) {
        setCurrentBaseRate(rate.toFloatOrNull() ?: return)
        rateItems = rateItems.mapIndexed { index, item ->
            if (index != 0) item.copy(currentRate = item.unitRate * rate.toFloat())
            else item.copy(currentRate = rate.toFloat())
        }
    }

    private fun getRates() {
        isLoading.postValue(true)
        viewModelScope.launch {
            getRatesFlow(Unit)
                .flowOn(Dispatchers.IO)
                .retry {
                    rateItems = emptyList()
                    isError.postValue(true)
                    true
                }
                .collect { items ->
                    isError.postValue(false)
                    rateItems = items
                    isLoading.postValue(false)
                }
        }
    }
}