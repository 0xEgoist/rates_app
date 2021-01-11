package com.example.skeletonapp.di.viewmodel

import androidx.lifecycle.ViewModel
import dagger.Module
import dagger.multibindings.Multibinds

@Module
abstract class CommonViewModelModule {
    @Multibinds
    abstract fun viewModels(): Map<Class<out ViewModel>, ViewModel>

    @Multibinds
    abstract fun assistedViewModels(): Map<Class<out ViewModel>, AssistedSavedStateViewModelFactory<out ViewModel>>
}