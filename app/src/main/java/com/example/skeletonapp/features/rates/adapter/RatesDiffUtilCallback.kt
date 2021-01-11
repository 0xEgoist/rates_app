package com.example.skeletonapp.features.rates.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.presentation.rates.RateItem

class RatesDiffUtilCallback : DiffUtil.ItemCallback<RateItem>() {

    override fun areItemsTheSame(oldItem: RateItem, newItem: RateItem): Boolean {
        return oldItem.ticker == newItem.ticker
    }

    override fun areContentsTheSame(oldItem: RateItem, newItem: RateItem): Boolean {
        return oldItem == newItem
    }

    override fun getChangePayload(oldItem: RateItem, newItem: RateItem): Any? {
        return CurrencyRateDifference(newItem.currentRate)
    }
}

data class CurrencyRateDifference(val newRate: Float)