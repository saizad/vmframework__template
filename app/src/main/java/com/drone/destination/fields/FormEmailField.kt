package com.drone.destination.fields

import com.sa.easyandroidform.fields.EmailField

class FormEmailField(
    fieldId: String,
    ogField: String? = null,
    isMandatory: Boolean = false
) : EmailField(fieldId, ogField, isMandatory) {

    var callBack: (String.() -> Unit)? = null

    override fun validate() {
        super.validate()
        callBack?.invoke(requiredField())
    }
}