package com.example.remote.entities

import com.google.gson.annotations.SerializedName
import java.util.*

data class UserResponse(
    @SerializedName("id")
    val id: String,

    @SerializedName("login")
    val login: String,

    @SerializedName("avatar_url")
    val avatarUrl: String,

    @SerializedName("name")
    val name: String?,

    @SerializedName("company")
    val company: String?,

    @SerializedName("blog")
    val blog: String?,

    var lastRefreshed: Date
)