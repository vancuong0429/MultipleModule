package com.example.home.di

import com.example.home.domain.GetTopUsers
import com.example.home.HomeRepositoryImpl
import com.example.home.HomeViewModel
import com.example.repository.AppDispatchers
import com.example.repository.HomeRepository
import kotlinx.coroutines.Dispatchers

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var createHomeModule = module {
    factory <HomeRepository>{ HomeRepositoryImpl(userDataSource = get(), userDao = get()) }

    factory{ GetTopUsers(homeRepository = get()) }
        single { AppDispatchers(Dispatchers.Main, Dispatchers.IO) }

    viewModel{ HomeViewModel(getTopUsers = get(), appDispatchers = get()) }



}