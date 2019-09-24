package com.template.repository.mapper

import com.template.common_jvm.extension.nullable.defaultEmpty
import com.template.common_jvm.mapper.Mapper
import com.template.domain.entities.UserEntity
import com.template.local.entities.UserDBO

class UserLocalEntityMapper : Mapper<UserDBO, UserEntity>() {
    override fun map(input: UserDBO): UserEntity = UserEntity(
        id = input.id,
        login = input.login,
        avatarUrl = input.avatarUrl,
        name = input.name.defaultEmpty(),
        company = input.company.defaultEmpty(),
        blog = input.blog.defaultEmpty(),
        lastRefreshed = input.lastRefreshed
    )
}