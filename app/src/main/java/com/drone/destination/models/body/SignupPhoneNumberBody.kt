package com.drone.destination.models.body

import com.drone.destination.fields.FormPhoneNumberField
import com.drone.destination.fields.MandatoryCheckedField

data class SignupPhoneNumberBody(
    val phone: String,
    val privacyPolicyAccepted: Boolean,
    val acceptTermOfUse: Boolean
) {

    companion object {
        const val PHONE = "phone"
        const val ACCEPT_PRIVACY_POLICY = "accept_privacy_policy"
        const val ACCEPT_TERM_OF_USE = "term_of_use"
    }

    class Form : PhoneNumberBody.Form {

        val acceptPrivacyPolicy: MandatoryCheckedField = requiredFindField(ACCEPT_PRIVACY_POLICY)
        val acceptTermOfUse: MandatoryCheckedField = requiredFindField(ACCEPT_TERM_OF_USE)

        constructor(fieldId: String) : super(
            fieldId,
            ArrayList(
                listOf(
                    FormPhoneNumberField(PHONE, null, true),
                    MandatoryCheckedField(ACCEPT_PRIVACY_POLICY, null, true),
                    MandatoryCheckedField(ACCEPT_TERM_OF_USE, null, true),
                )
            )
        )

        constructor(fieldId: String, signupPhoneNumberBody: SignupPhoneNumberBody) : super(
            fieldId,
            ArrayList(
                listOf(
                    FormPhoneNumberField(PHONE, signupPhoneNumberBody.phone, true),
                    MandatoryCheckedField(
                        ACCEPT_PRIVACY_POLICY,
                        signupPhoneNumberBody.privacyPolicyAccepted,
                        true
                    ),
                    MandatoryCheckedField(ACCEPT_TERM_OF_USE, signupPhoneNumberBody.acceptTermOfUse, true)
                )
            )
        )


        override fun buildForm(): PhoneNumberBody {
            return PhoneNumberBody(phoneField.strip0MobileNumber())
        }
    }
}