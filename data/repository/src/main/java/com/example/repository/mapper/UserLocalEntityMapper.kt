package com.example.repository.mapper

import com.example.common_jvm.mapper.Mapper
import com.example.local.entities.UserDBO
import com.example.remote.entities.UserResponse

class UserResponseLocalMapper : Mapper<UserResponse, UserDBO>(){
    override fun map(input: UserResponse): UserDBO = UserDBO(
        id = input.id,
        login = input.login,
        avatarUrl = input.avatarUrl,
        name = input.name,
        company = input.company,
        blog = input.blog,
        lastRefreshed = input.lastRefreshed
    )

}