package com.shaadi.assignment.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shaadi.assignment.db.MatchDetail
import com.shaadi.assignment.models.Match
import kotlinx.coroutines.flow.Flow

@Dao
interface MatchesDao {

    @Query("SELECT * FROM `Match` WHERE toId = :toId")
    fun getAll(toId: Int): Flow<List<MatchDetail>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<Match>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: Match): Long
}