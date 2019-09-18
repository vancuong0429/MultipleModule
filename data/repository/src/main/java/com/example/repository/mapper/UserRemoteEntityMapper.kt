package com.example.repository.mapper

import com.example.common_jvm.mapper.Mapper
import com.example.domain.entities.UserEntity
import com.example.local.entities.UserDBO
import com.example.remote.entities.UserResponse

class UserEntityRemoteMapper : Mapper<UserResponse, UserEntity>() {
    override fun map(input: UserResponse): UserEntity = UserEntity(
        id = input.id,
        login = input.login,
        avatarUrl = input.avatarUrl,
        name = input.name,
        company = input.company,
        blog = input.blog,
        lastRefreshed = input.lastRefreshed
    )

    fun mapDBOToEntity(data: UserDBO): UserEntity = UserEntity(
        id = data.id,
        login = data.login,
        avatarUrl = data.avatarUrl,
        name = data.name,
        company = data.company,
        blog = data.blog,
        lastRefreshed = data.lastRefreshed
    )


    fun mapFromResponseToDBO(data: UserResponse): UserDBO = UserDBO(
        id = data.id,
        login = data.login,
        avatarUrl = data.avatarUrl,
        name = data.name,
        company = data.company,
        blog = data.blog,
        lastRefreshed = data.lastRefreshed
    )
}