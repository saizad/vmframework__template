package com.drone.destination.fields

import com.drone.destination.utils.isFullNameValid
import com.drone.destination.utils.name
import com.sa.easyandroidform.StringUtils
import com.sa.easyandroidform.fields.NonEmptyStringField
import io.reactivex.exceptions.CompositeException

class FullNameField(
    fieldId: String,
    ogField: String?,
    isMandatory: Boolean = true
) : NonEmptyStringField(fieldId, ogField, isMandatory) {

    val firstName: String?
        get() = getName()

    val lastName: String?
        get() = getName(false)

    private fun getName(firstName: Boolean = true): String? {
        if (isValid) {
           return field?.name(firstName)
        }
        return null
    }

    @Throws(CompositeException::class)
    override fun validate() {
        super.validate()
        field?.let {
            val split = StringUtils.stripTrailingLeadingNewLines(it).split(" ".toRegex())
            if (split.size < 2) {
                throw CompositeException(Exception("Invalid Name format. Use first name followed by last name and space in between."))
            }
            isFullNameValid(it)
        }
    }
}