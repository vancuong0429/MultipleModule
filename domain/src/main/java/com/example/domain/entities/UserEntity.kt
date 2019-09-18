package com.example.domain.entities

import java.util.*
import java.util.concurrent.TimeUnit

data class UserEntity(
    val id: String,

    val login: String,

    val avatarUrl: String,

    val name: String?,

    val company: String?,

    val blog: String?,

    var lastRefreshed: Date?
)