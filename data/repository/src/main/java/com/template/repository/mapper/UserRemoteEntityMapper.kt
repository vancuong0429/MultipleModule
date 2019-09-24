package com.template.repository.mapper

import com.template.common_jvm.extension.nullable.defaultEmpty
import com.template.common_jvm.mapper.Mapper
import com.template.domain.entities.UserEntity
import com.template.remote.entities.UserResponse
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