package com.example.detail.domain

import com.example.detail.domain.UseCase
import com.example.model.views.User
import com.example.repository.Resource
import com.example.repository.UserDetailRepository

class GetUserDetailCase(private val detailRepository: UserDetailRepository) : UseCase<User>() {
    override suspend fun run(login: String): Resource<User> {
        return detailRepository.getUserDetail(login)
    }

}