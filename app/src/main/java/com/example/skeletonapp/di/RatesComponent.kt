package com.example.skeletonapp.di

import com.example.skeletonapp.features.rates.RatesFragment
import dagger.Subcomponent

@Subcomponent
interface RatesComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): RatesComponent
    }

    fun inject(ratesFragment: RatesFragment)
}