package com.example.data.repository.remote.rates.models

import com.google.gson.annotations.SerializedName

typealias IsoCode = String
typealias Rate = Float

data class RatesResponse(
    @SerializedName("baseCurrency") val baseCurrency: String,
    @SerializedName("rates") val rates: Map<IsoCode, Rate>
)