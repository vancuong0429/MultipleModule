package com.example.repository

import com.example.model.views.User

interface UserDetailRepository  {
    suspend fun getUserDetail(login: String): Resource<User>
}