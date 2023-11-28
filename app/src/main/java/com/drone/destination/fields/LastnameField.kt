package com.drone.destination.fields

import com.drone.destination.utils.isFirstNameValid
import com.sa.easyandroidform.fields.NonEmptyStringField
import io.reactivex.exceptions.CompositeException

class LastnameField(
    fieldId: String,
    ogField: String? = null,
    isMandatory: Boolean = false,
) : NonEmptyStringField(fieldId, ogField, isMandatory) {

    @Throws(CompositeException::class)
    override fun validate() {
        super.validate()
        isFirstNameValid(field)
    }
}