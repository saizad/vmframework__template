package com.shaadi.assignment.repo

import com.shaadi.assignment.db.ShaadiDatabase
import com.shaadi.assignment.models.AssignmentUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class UsersRepo @Inject constructor(
    private val db: ShaadiDatabase
) {

    fun allUsers(): Flow<List<AssignmentUser>> {
        return db.assignmentUserDao().getAll()
            .onEach {
                if (it.isEmpty()) {
                    val items = (1..9).map { AssignmentUser(it) }
                    db.assignmentUserDao().insertAll(items)
                }
            }
    }

}