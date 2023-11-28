package com.drone.destination.models

import android.os.Parcelable
import com.sa.easyandroidform.fields.MandatoryIntegerField
import com.sa.easyandroidform.fields.MandatoryStringField
import com.sa.easyandroidform.fields.StringField
import com.sa.easyandroidform.form.FormModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class Address(
    val id: Int,
    val line1: String?,
    val line2: String?,
    val landmark: String?,
    val area: String?,
    val pincode: Int,
    val city: String,
    val tehsil: String,
    val district: String,
    val state: String,
    val country: String,
) : Parcelable {

    companion object {
        val FAKE = Address(-1, "Pinnacle Business Park, Mahakali Caves Rd, Shanti Nagar, Andheri East", null, null, "Shanti Nagar", 400093, "Mumbai", "MIDC","Mumbai", "Maharashtra", "India" )
    }


    class Form : FormModel<Address> {

        companion object {
            const val LINE1 = "line1"
            const val LINE2 = "line2"
            const val LANDMARK = "landmark"
            const val AREA = "area"
            const val PINCODE = "pincode"
            const val CITY = "city"
            const val TEHSIL = "tehsil"
            const val DISTRICT = "district"
            const val STATE = "state"
            const val COUNTRY = "country"
        }

        val line1Field = requiredFindField<StringField>(LINE1)
        val line2Field = requiredFindField<StringField>(LINE2)
        val landmarkField = requiredFindField<StringField>(LANDMARK)
        val areaField = requiredFindField<StringField>(AREA)
        val pincodeField = requiredFindField<MandatoryIntegerField>(PINCODE)
        val cityField = requiredFindField<MandatoryStringField>(CITY)
        val tehsilField = requiredFindField<MandatoryStringField>(TEHSIL)
        val districtField = requiredFindField<MandatoryStringField>(DISTRICT)
        val stateField = requiredFindField<MandatoryStringField>(STATE)
        val countryField = requiredFindField<MandatoryStringField>(COUNTRY)
        private var id: Int = 0

        constructor(fieldId: String) : super(
            fieldId,
            ArrayList(
                listOf(
                    StringField(LINE1),
                    StringField(LINE2),
                    StringField(LANDMARK),
                    StringField(AREA),
                    MandatoryIntegerField(PINCODE),
                    MandatoryStringField(CITY),
                    MandatoryStringField(TEHSIL),
                    MandatoryStringField(DISTRICT),
                    MandatoryStringField(STATE),
                    MandatoryStringField(COUNTRY, "India"),
                )
            )
        )

        constructor(fieldId: String, address: Address) : super(
            fieldId,
            ArrayList(
                listOf(
                    StringField(LINE1, address.line1),
                    StringField(LINE2, address.line2),
                    StringField(LANDMARK, address.landmark),
                    StringField(AREA, address.area),
                    MandatoryIntegerField(PINCODE, address.pincode),
                    MandatoryStringField(CITY, address.city),
                    MandatoryStringField(TEHSIL, address.tehsil),
                    MandatoryStringField(DISTRICT, address.district),
                    MandatoryStringField(STATE, address.state),
                    MandatoryStringField(COUNTRY, address.country),
                )
            )
        ) {
            id = address.id
        }

        fun updatePincodeLocation(address: Address) {
            pincodeField.field = address.pincode
            cityField.field = address.city
            tehsilField.field = address.tehsil
            districtField.field = address.district
            stateField.field = address.state
        }

        override fun buildForm(): Address {
            return Address(
                id,
                line1Field.field,
                line2Field.field,
                landmarkField.field,
                areaField.field,
                pincodeField.requiredField(),
                cityField.requiredField(),
                tehsilField.requiredField(),
                districtField.requiredField(),
                stateField.requiredField(),
                countryField.requiredField()
            )
        }
    }
}
