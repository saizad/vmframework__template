package com.drone.destination.fields

import com.drone.destination.utils.isPhoneValid
import com.sa.easyandroidform.fields.NonEmptyStringField
import io.reactivex.exceptions.CompositeException

class FormPhoneNumberField(
    fieldId: String,
    ogField: String? = null,
    isMandatory: Boolean = false
) : NonEmptyStringField(fieldId, ogField, isMandatory) {

    var callBack: (String.() -> Unit)? = null

    override fun validate() {
        super.validate()
        if(!isPhoneValid(field)){
            if(!field.isNullOrBlank()){
                if(requiredField().length > 10){
                    throw CompositeException(Exception("Phone number cannot be greater than 10 digits."))
                }
                throw CompositeException(Exception("Phone number cannot be lesser than 10 digits."))
            }
        }
        callBack?.invoke(requiredField())
    }

    fun strip0MobileNumber(): String {
        return super.requiredField().trimStart { it.toString() == "0" }
    }
}