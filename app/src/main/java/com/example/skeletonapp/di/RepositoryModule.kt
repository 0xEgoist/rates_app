package com.example.skeletonapp.di

import com.example.data.repository.repositories.rates.RatesRepository
import com.example.domain.repository.IRatesRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {

    @Binds
    @Singleton
    fun provideRatesRepository(
        ratesRepository: RatesRepository
    ): IRatesRepository
}