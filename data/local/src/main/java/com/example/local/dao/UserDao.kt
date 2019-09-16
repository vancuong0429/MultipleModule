package com.example.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.local.entities.UserDBO

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(userDBO: UserDBO)

    @Insert
    suspend fun insertUses(userDBOS: List<UserDBO>)

    @Query("SELECT * FROM UserDBO ORDER BY login ASC LIMIT 30")
    suspend fun getTopUsers(): List<UserDBO>
}