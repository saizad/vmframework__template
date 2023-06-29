package com.shaadi.assignment.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Name(val title: String, val first: String, val last: String) : Parcelable {

    val fullName: String
        get() = "$first $last"

}
