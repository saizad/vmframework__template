package com.shaadi.assignment.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(
    foreignKeys = [ForeignKey(
        entity = ShaadiUser::class, parentColumns = ["id"], childColumns = ["fromId"],
        onDelete = ForeignKey.CASCADE
    ),
        ForeignKey(
            entity = AssignmentUser::class, parentColumns = ["id"], childColumns = ["toId"],
            onDelete = ForeignKey.CASCADE
        )]
)
@Parcelize
data class Match(
    @PrimaryKey(autoGenerate = true)
    val id: Int, val fromId: Int, val toId: Int, val isAccepted: Boolean? = null
) : Parcelable {

    @Ignore
    lateinit var from: ShaadiUser

}

