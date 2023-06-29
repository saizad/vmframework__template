package com.shaadi.assignment.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shaadi.assignment.models.ShaadiUser
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ShaadiUsersDao {

    @Query("SELECT * FROM ShaadiUser")
    abstract fun getAll(): Flow<List<ShaadiUser>>

    @Query("SELECT * FROM ShaadiUser where id IN (:ids)")
    abstract suspend fun getAll(ids: List<Long>): List<ShaadiUser>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAll(items: List<ShaadiUser>): List<Long>

     suspend fun insertAndGet(items: List<ShaadiUser>): List<ShaadiUser> {
        return getAll(insertAll(items))
    }
}