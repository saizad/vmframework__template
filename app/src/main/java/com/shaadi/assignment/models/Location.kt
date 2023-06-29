package com.shaadi.assignment.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Location(val city: String, val state: String?, val country: String) : Parcelable {

    val location: String get() {
       return if(state != null) {
            "${city}, ${state}, ${country}"
        } else {
            "${city}, ${country}"
        }
    }
}
