package com.example.skeletonapp.di

import android.content.Context
import com.example.skeletonapp.di.viewmodel.CommonViewModelModule
import com.example.skeletonapp.di.viewmodel.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RepositoryModule::class,
        NetworkModule::class,
        ViewModelModule::class,
        CommonViewModelModule::class
    ]
)
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }

    fun ratesComponent(): RatesComponent.Factory
}