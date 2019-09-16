package com.example.detail.di

import com.example.detail.DetailViewModel
import com.example.domain.repositories.UserDetailRepository
import com.example.domain.usecases.GetUserDetailUseCase
import com.example.repository.DetailRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var createDetailModule = module{
    factory <UserDetailRepository>{ DetailRepositoryImpl(userDataSource = get()) }

    factory{ GetUserDetailUseCase(detailRepository = get()) }

    viewModel{ DetailViewModel(getUserDetailUseCase = get(), appDispatchers = get()) }
}