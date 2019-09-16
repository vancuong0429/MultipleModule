package com.example.repository.mapper

import com.example.domain.entities.UserEntity
import com.example.local.entities.UserDBO
import com.example.remote.entities.UserResponse

class UserEntityMapper{
    fun mapDBOToEntity(data: UserDBO): UserEntity = UserEntity(
        id = data.id,
        login = data.login,
        avatarUrl = data.avatarUrl,
        name = data.name,
        company = data.company,
        blog = data.blog,
        lastRefreshed = data.lastRefreshed
    )

    fun mapResponseToEntity(data: UserResponse): UserEntity = UserEntity(
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