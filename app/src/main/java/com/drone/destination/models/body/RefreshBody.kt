package com.drone.destination.models.body

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RefreshBody(val refresh: String) : Parcelable
