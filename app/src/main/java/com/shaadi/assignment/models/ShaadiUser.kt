package com.shaadi.assignment.models

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class ShaadiUser(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("_id")
    val id: Int,
    @Embedded
    val name: Name,
    @Embedded
    val picture: Picture,
    @Embedded
    val location: Location,
    val gender: String,
    val email: String
) : Parcelable

