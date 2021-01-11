package com.example.skeletonapp.core.view

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.skeletonapp.core.presenter.BaseViewModel
import com.example.skeletonapp.di.viewmodel.InjectingSavedStateViewModelFactory
import java.lang.reflect.ParameterizedType
import javax.inject.Inject

abstract class BaseFragment<VM : BaseViewModel>(@LayoutRes layoutResId: Int) :
    Fragment(layoutResId) {

    @Inject
    lateinit var defaultViewModelFactory: InjectingSavedStateViewModelFactory

    protected lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(getVMClass())
    }

    override fun getDefaultViewModelProviderFactory(): ViewModelProvider.Factory =
        defaultViewModelFactory.create(this, arguments)

    @Suppress("UNCHECKED_CAST")
    private fun getVMClass(): Class<VM> {
        val viewModelClass = (javaClass.genericSuperclass as ParameterizedType)
            .actualTypeArguments.first() as? Class<VM>
        return viewModelClass
            ?: throw AssertionError("ViewModel class $viewModelClass was not found")
    }
}