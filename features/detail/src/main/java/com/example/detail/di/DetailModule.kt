package com.example.detail.di

import com.example.detail.DetailRepositoryImpl
import com.example.detail.DetailViewModel
import com.example.detail.domain.GetUserDetailCase
import com.example.repository.UserDetailRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var createDetailModule = module{
    factory <UserDetailRepository>{ DetailRepositoryImpl(userDataSource = get()) }

    factory{ GetUserDetailCase(detailRepository = get()) }

    viewModel{ DetailViewModel(getUserDetailCase = get(), appDispatchers = get()) }
}