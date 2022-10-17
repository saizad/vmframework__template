package com.agrimguru.ed.models

import android.os.Parcelable
import com.sa.easyandroidform.fields.MandatoryStringField
import com.sa.easyandroidform.form.FormModel
import com.vm.framework.components.form.fields.NameField
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AgrimGuruUser(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val avatar: String,
    val job: String? = null
) : Parcelable {

    val fullName: String
        get() = "$firstName $lastName"


    open class Form : FormModel<AgrimGuruUser> {

        companion object {
            const val FULL_NAME = "full_name"
            const val JOB = "job"
        }

        val fullNameField: NameField = requiredFindField(FULL_NAME)
        val jobField: MandatoryStringField = requiredFindField(JOB)
        private val reqResUser: AgrimGuruUser

        constructor(reqResUser: AgrimGuruUser) : super(
            ArrayList(
                listOf(
                    NameField(FULL_NAME, reqResUser.fullName, true),
                    MandatoryStringField(JOB, reqResUser.job)
                )
            )
        ) {
            this.reqResUser = reqResUser
        }

        override fun buildForm(): AgrimGuruUser {
            return AgrimGuruUser(
                reqResUser.id,
                fullNameField.firstName()!!,
                fullNameField.lastName()!!,
                reqResUser.email,
                reqResUser.avatar,
                jobField.requiredField()
            )
        }
    }
}

