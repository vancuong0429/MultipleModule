package com.example.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.model.views.User

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: User)

    @Insert
    suspend fun insertUses(users: List<User>)

    @Query("SELECT * FROM User ORDER BY login ASC LIMIT 30")
    suspend fun getTopUsers(): List<User>
}