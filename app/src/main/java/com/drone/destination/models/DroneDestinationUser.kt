package com.drone.destination.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DroneDestinationUser(
    val id: Int,
    val email: String
) : Parcelable

