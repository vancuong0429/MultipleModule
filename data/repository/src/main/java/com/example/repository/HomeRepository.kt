package com.example.repository

import com.example.model.views.User

interface HomeRepository  {
     suspend fun getTopUsers(): Resource<List<User>>

     suspend fun getUserDetail(forceRefresh: Boolean = false, login: String): Resource<User>
}