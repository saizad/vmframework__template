package com.drone.destination.fields

import com.sa.easyandroidform.fields.BooleanField
import io.reactivex.exceptions.CompositeException

class MandatoryCheckedField(
    fieldId: String,
    ogField: Boolean? = null,
    isMandatory: Boolean = false
) : BooleanField(fieldId, ogField, isMandatory) {

    @Throws(CompositeException::class)
    override fun validate() {
        super.validate()
        if (field != true)  {
            throw CompositeException(Exception("Condition not accepted"))
        }
    }
}