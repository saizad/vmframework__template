package com.shaadi.assignment.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Picture(val thumbnail: String, val medium: String, val large: String) : Parcelable
