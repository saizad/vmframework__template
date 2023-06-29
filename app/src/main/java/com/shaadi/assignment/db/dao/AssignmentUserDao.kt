package com.shaadi.assignment.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shaadi.assignment.models.AssignmentUser
import kotlinx.coroutines.flow.Flow

@Dao
interface AssignmentUserDao {

    @Query("SELECT * FROM AssignmentUser")
    fun getAll(): Flow<List<AssignmentUser>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<AssignmentUser>)

}