package com.example.home.di

import com.example.home.HomeViewModel

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val createHomeModule = module {

    viewModel{ HomeViewModel(getTopUsersUseCase = get(), appDispatchers = get()) }

}