package com.example.remote

import com.example.model.ApiResult
import com.example.model.views.User
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {
    @GET("search/users")
    suspend fun fetchTopUsersAsync(@Query("q") query: String = "PhilippeB",
                           @Query("sort") sort: String = "followers"): ApiResult<User>

    @GET("users/{login}")
    suspend fun fetchUserDetailsAsync(@Path("login") login: String): User

}