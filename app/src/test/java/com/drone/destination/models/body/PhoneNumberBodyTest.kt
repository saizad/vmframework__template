package com.drone.destination.models.body

import com.drone.destination.utils.setRandom
import com.sa.easyandroidform.fields.BaseField
import com.sa.easyandroidform.form.BaseModelFormTest


class PhoneNumberBodyTest: BaseModelFormTest<PhoneNumberBody.Form>() {
    override fun initForm(): PhoneNumberBody.Form {
        return PhoneNumberBody.Form("")
    }

    override fun changeFormFieldToAnyValue(): BaseField<*> {
        form.phoneField.field = "9146916727"
        return form.phoneField
    }

    override fun setValidValue(field: BaseField<*>?) {
        setRandom(field)
    }

}