package com.example.home.di

import com.example.home.HomeViewModel
import com.example.common.AppDispatchers
import com.example.domain.repositories.HomeRepository
import com.example.domain.usecases.GetTopUsersUseCase
import com.example.repository.HomeRepositoryImpl
import kotlinx.coroutines.Dispatchers

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var createHomeModule = module {
    factory <HomeRepository>{ HomeRepositoryImpl(userDataSource = get(), userDao = get()) }

    factory{ GetTopUsersUseCase(homeRepository = get()) }
        single { AppDispatchers(Dispatchers.Main, Dispatchers.IO) }

    viewModel{ HomeViewModel(getTopUsersUseCase = get(), appDispatchers = get()) }



}