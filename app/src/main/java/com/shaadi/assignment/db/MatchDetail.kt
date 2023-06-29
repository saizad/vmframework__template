package com.shaadi.assignment.db

import androidx.room.Embedded
import androidx.room.Ignore
import androidx.room.Relation
import com.shaadi.assignment.models.Match
import com.shaadi.assignment.models.ShaadiUser

data class MatchDetail(
    @Embedded private val match: Match,

    @Relation(
        parentColumn = "fromId",
        entityColumn = "id",
        entity = ShaadiUser::class
    )
    private val shaadiUser: ShaadiUser,

    ) {
    @Ignore
    fun match(): Match {
        return match.apply {
            this.from = this@MatchDetail.shaadiUser
        }
    }
}
