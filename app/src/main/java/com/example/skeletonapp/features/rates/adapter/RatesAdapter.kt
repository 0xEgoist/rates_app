package com.example.skeletonapp.features.rates.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.domain.presentation.rates.RateItem
import com.example.skeletonapp.databinding.ItemRateBinding

class RatesAdapter(
    private val onCurrencyRateClicked: (Int) -> Unit,
    private val onCurrencyRateChanged: (String) -> Unit
) : ListAdapter<RateItem, RatesViewHolder>(RatesDiffUtilCallback()) {

    private val rateChangedListener = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit

        override fun onTextChanged(rate: CharSequence?, p1: Int, p2: Int, p3: Int) {
            if (!rate.isNullOrBlank()) {
                onCurrencyRateChanged(rate.toString())
            }
        }

        override fun afterTextChanged(editable: Editable?) = Unit
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatesViewHolder {
        val binding = ItemRateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewHolder = RatesViewHolder(binding)
        viewHolder.itemView.setOnClickListener {
            if (viewHolder.adapterPosition != 0) binding.currencyRate.requestFocus()
        }
        binding.currencyRate.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                onCurrencyRateClicked(viewHolder.adapterPosition)
                binding.currencyRate.addTextChangedListener(rateChangedListener)
            } else {
                binding.currencyRate.removeTextChangedListener(rateChangedListener)
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: RatesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onBindViewHolder(
        holder: RatesViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) onBindViewHolder(holder, position)
        else with(holder.binding.currencyRate) {
            if (!isFocused) setText((payloads.first() as CurrencyRateDifference).newRate.toString())
        }
    }
}