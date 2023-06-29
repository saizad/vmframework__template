package com.shaadi.assignment.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class AssignmentUser(
    @PrimaryKey
    val id: Int
) : Parcelable

