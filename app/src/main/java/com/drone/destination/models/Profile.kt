package com.drone.destination.models

import android.os.Parcelable
import com.drone.destination.fields.FullNameField
import com.sa.easyandroidform.fields.GenderField
import com.sa.easyandroidform.form.FormModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class Profile(
    val id: Int,
    val name: String,
    val avatar: String? = null,
    val gender: Int? = null,
    val genderStr: String? = null
) :
    Parcelable {

    companion object {
        val FAKE = Profile(-1, "John Doe")
    }


    class Form : FormModel<Profile> {

        companion object {
            const val GENDER = "gender"
            private const val NAME = "name"
        }

        val nameField = requiredFindField<FullNameField>(NAME)
        val genderField = requiredFindField<GenderField>(GENDER)
        var id = 0

        constructor(fieldId: String, profile: Profile) : super(
            fieldId, ArrayList(
                listOf(
                    GenderField(
                        GENDER,
                        profile.genderStr,
                        false,
                        Gender.values().map { it.value }),
                    FullNameField(NAME, profile.name)
                )
            )
        ) {
            id = profile.id
        }

        constructor(fieldId: String) : super(
            fieldId, ArrayList(
                listOf(
                    GenderField(
                        GENDER,
                        null,
                        false,
                        Gender.values().map { it.value }),
                    FullNameField(NAME, null)
                )
            )
        )

        override fun buildForm(): Profile {
            val gender =
                Gender.values().firstOrNull { genderField.field?.equals(it.value, true) == true }
            return Profile(id, nameField.requiredField(), gender = gender?.id)
        }
    }

    enum class Gender(val id: Int, val value: String) {
        MALE(1, "male"),
        FEMALE(2, "female")
    }
}
