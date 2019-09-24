package com.template.repository.mapper

import com.template.common_jvm.extension.nullable.defaultEmpty
import com.template.common_jvm.mapper.Mapper
import com.template.local.entities.UserDBO
import com.template.remote.entities.UserResponse
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