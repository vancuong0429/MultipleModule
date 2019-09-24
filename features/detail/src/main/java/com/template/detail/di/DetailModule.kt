package com.template.detail.di

import com.template.detail.DetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var createDetailModule = module{
    viewModel{ DetailViewModel(getUserDetailUseCase = get(), appDispatchers = get()) }
}