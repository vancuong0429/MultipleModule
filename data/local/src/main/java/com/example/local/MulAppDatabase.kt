package com.example.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.local.dao.UserDao
import com.example.local.entities.UserDBO

@Database(entities = [UserDBO::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MulAppDatabase  : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        fun buildDatabase(context: Context) = Room.databaseBuilder(context.applicationContext, MulAppDatabase::class.java, "Mul.db")
            .build()
    }
}