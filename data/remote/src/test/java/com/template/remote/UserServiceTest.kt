package com.template.remote

import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.net.HttpURLConnection

class UserServiceTest : BaseTest() {
    @Test
    fun `search top users by name`() {
        mockHttpResponse(mockServer, "search_users.json", HttpURLConnection.HTTP_OK)
        runBlocking {
            val users = userDataSource.fetchTopUsersAsync()
            assertEquals(1, users.items.size)
            assertEquals("6847959", users.items.first().id)
            assertEquals("PhilippeBoisney", users.items.first().login)
            assertEquals(
                "https://avatars0.githubusercontent.com/u/6847959?v=4",
                users.items.first().avatarUrl
            )
        }
    }
}