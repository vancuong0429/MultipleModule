package com.example.repository.mapper

import com.example.common_jvm.extension.nullable.defaultEmpty
import com.example.common_jvm.mapper.Mapper
import com.example.domain.entities.UserEntity
import com.example.remote.entities.UserResponse
import java.util.*

class UserRemoteEntityMapper : Mapper<UserResponse, UserEntity>() {
    override fun map(input: UserResponse): UserEntity = UserEntity(
        id = input.id.defaultEmpty(),
        login = input.login.defaultEmpty(),
        avatarUrl = input.avatarUrl.defaultEmpty(),
        name = input.name.defaultEmpty(),
        company = input.company.defaultEmpty(),
        blog = input.blog.defaultEmpty(),
        lastRefreshed = Date()
    )
}