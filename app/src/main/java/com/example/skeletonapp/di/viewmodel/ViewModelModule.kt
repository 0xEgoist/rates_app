package com.example.skeletonapp.di.viewmodel

import androidx.lifecycle.ViewModel
import com.example.skeletonapp.features.rates.RatesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(RatesViewModel::class)
    fun bindsRatesViewModel(ratesViewModel: RatesViewModel): ViewModel
}