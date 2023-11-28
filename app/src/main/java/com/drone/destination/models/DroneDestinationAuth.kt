package com.drone.destination.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DroneDestinationAuth(
    val refresh: String,
    val access: String,
    val user: DroneDestinationUser
) : Parcelable
