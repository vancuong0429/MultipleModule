package com.template.remote

import com.template.remote.entities.ApiResult
import com.template.remote.entities.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {
    @GET("search/users")
    suspend fun fetchTopUsersAsync(
        @Query("q") query: String = "PhilippeB",
        @Query("sort") sort: String = "followers"
    ): ApiResult<UserResponse>

    @GET("users/{login}")
    suspend fun fetchUserDetailsAsync(@Path("login") login: String): UserResponse

}