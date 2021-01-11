package com.example.skeletonapp.features.rates.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.domain.presentation.rates.RateItem
import com.example.skeletonapp.databinding.ItemRateBinding


class RatesViewHolder(
    val binding: ItemRateBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: RateItem) {
        binding.currencyFlagEmoji.text = item.flagEmoji
        binding.currencyTicker.text = item.ticker
        binding.currencyName.text = item.name
        with(binding.currencyRate) {
            if (!isFocused) setText(item.currentRate.toString())
        }
    }
}