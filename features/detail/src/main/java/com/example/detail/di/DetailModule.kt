package com.example.detail.di

import com.example.detail.DetailViewModel
import com.example.domain.usecases.GetUserDetailUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var createDetailModule = module{
    viewModel{ DetailViewModel(getUserDetailUseCase = get(), appDispatchers = get()) }
}