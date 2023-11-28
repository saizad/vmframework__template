package com.drone.destination.models.body

import com.drone.destination.fields.FormPhoneNumberField
import com.sa.easyandroidform.fields.BaseField
import com.sa.easyandroidform.form.FormModel

data class PhoneNumberBody(val phone: String) {

    companion object {
        const val PHONE = "phone"
    }

    open class Form : FormModel<PhoneNumberBody> {

        val phoneField: FormPhoneNumberField = requiredFindField(PHONE)

        constructor(fieldId: String) : this(
            fieldId,
            listOf(
                FormPhoneNumberField(PHONE, null, true),
            )
        )

        constructor(fieldId: String, phoneNumberBody: PhoneNumberBody) : this(
            fieldId,
            listOf(
                FormPhoneNumberField(PHONE, phoneNumberBody.phone, true)
            )
        )

        constructor(fieldId: String, list: List<BaseField<*>>) : super(
            fieldId,
            ArrayList(list)
        )


        override fun buildForm(): PhoneNumberBody {
            return PhoneNumberBody(phoneField.strip0MobileNumber())
        }
    }
}