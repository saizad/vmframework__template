package com.shaadi.assignment.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shaadi.assignment.db.ShaadiDatabase.Companion.LATEST_VERSION
import com.shaadi.assignment.db.dao.AssignmentUserDao
import com.shaadi.assignment.db.dao.MatchesDao
import com.shaadi.assignment.db.dao.ShaadiUsersDao
import com.shaadi.assignment.models.AssignmentUser
import com.shaadi.assignment.models.Match
import com.shaadi.assignment.models.ShaadiUser

@Database(
    entities = [AssignmentUser::class, ShaadiUser::class, Match::class],
    version = LATEST_VERSION
)
abstract class ShaadiDatabase : RoomDatabase() {

    companion object {
        const val LATEST_VERSION = 1
    }

    abstract fun assignmentUserDao(): AssignmentUserDao

    abstract fun shaadiUsersDao(): ShaadiUsersDao

    abstract fun matchesDao(): MatchesDao

}