package com.example.repository.mapper

import com.example.common_jvm.extension.nullable.defaultEmpty
import com.example.common_jvm.mapper.Mapper
import com.example.local.entities.UserDBO
import com.example.remote.entities.UserResponse
import java.util.Date

class UserResponseLocalMapper : Mapper<UserResponse, UserDBO>(){
    override fun map(input: UserResponse): UserDBO = UserDBO(
        id = input.id.defaultEmpty(),
        login = input.login.defaultEmpty(),
        avatarUrl = input.avatarUrl.defaultEmpty(),
        name = input.name,
        company = input.company,
        blog = input.blog,
        lastRefreshed = Date()
    )
}