package com.drone.destination.models

import android.os.Parcelable
import com.sa.easyandroidform.fields.EmailField
import com.sa.easyandroidform.form.FormModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class DroneDestinationUser(
    val id: Int,
    val mobile: String,
    val profile: Profile,
    val address: Address,
    val email: String?,
    private val token: String
) : Parcelable {

    companion object {
        const val EMAIL_KEY = "email"
        const val PROFILE = "profile"
        const val ADDRESS = "address"

        val FAKE = DroneDestinationUser(
            -1,
            "98${(12345678..99999999).random()}",
            Profile.FAKE,
            Address.FAKE,
            null,
            ""
        )
    }

    class Form : FormModel<DroneDestinationUser> {
        val emailField: EmailField = requiredFindField(EMAIL_KEY)
        val profileForm: Profile.Form = requiredFindField(PROFILE)
        val addressForm: Address.Form = requiredFindField(ADDRESS)
        val token: String

        constructor(token: String, user: DroneDestinationUser) : super(
            ArrayList(
                listOf(
                    EmailField(EMAIL_KEY, user.email),
                    Profile.Form(PROFILE, user.profile),
                    Address.Form(ADDRESS, user.address)
                )
            )
        ) {
            this.token = token
        }


        constructor(token: String) : super(
            ArrayList(
                listOf(
                    EmailField(EMAIL_KEY),
                    Profile.Form(PROFILE),
                    Address.Form(ADDRESS)
                )
            )
        ) {
            this.token = token
        }

        override fun buildForm(): DroneDestinationUser {
            val studentProfile = profileForm.requiredBuild()
            val address = addressForm.requiredBuild()

            return DroneDestinationUser(
                0,
                "",
                studentProfile,
                address,
                emailField.field,
                token
            )
        }
    }
}

