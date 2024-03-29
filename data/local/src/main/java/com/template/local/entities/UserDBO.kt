package com.template.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class UserDBO(
    @PrimaryKey
    val id: String,

    val login: String,

    val avatarUrl: String,

    val name: String?,

    val company: String?,

    val blog: String?,

    var lastRefreshed: Date
)
