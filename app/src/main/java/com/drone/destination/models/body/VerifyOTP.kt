package com.drone.destination.models.body

import com.drone.destination.models.Token
import com.sa.easyandroidform.fields.MandatoryIntegerField
import com.sa.easyandroidform.form.FormModel

class VerifyOTP(
    val token: String,
    val otp: Int
) {

    class Form(private val token: Token) : FormModel<VerifyOTP>(
        ArrayList(
            listOf(
                MandatoryIntegerField("otp1"),
                MandatoryIntegerField("otp2"),
                MandatoryIntegerField("otp3"),
                MandatoryIntegerField("otp4"),
            )
        )
    ) {

        val otp1Field: MandatoryIntegerField = requiredFindField("otp1")
        val otp2Field: MandatoryIntegerField = requiredFindField(("otp2"))
        val otp3Field: MandatoryIntegerField = requiredFindField(("otp3"))
        val otp4Field: MandatoryIntegerField = requiredFindField(("otp4"))


        fun autoFill(code: String){
            val otp = code.toCharArray()
            otp1Field.field = otp[0].toString().toInt()
            otp2Field.field = otp[1].toString().toInt()
            otp3Field.field = otp[2].toString().toInt()
            otp4Field.field = otp[3].toString().toInt()
        }

        override fun buildForm(): VerifyOTP {

            val code =
                "${otp1Field.requiredField()}${otp2Field.requiredField()}${otp3Field.requiredField()}${otp4Field.requiredField()}"

            return VerifyOTP(token.token, code.toInt())
        }
    }
}