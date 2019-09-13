package com.example.remote

class UserDataSource(private val userService: UserService) {

     suspend fun fetchTopUsersAsync() = userService.fetchTopUsersAsync()

     suspend fun fetchUserDetailsAsync(login: String) =
        userService.fetchUserDetailsAsync(login)
}