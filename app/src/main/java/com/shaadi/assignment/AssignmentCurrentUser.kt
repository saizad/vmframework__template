package com.shaadi.assignment

import com.shaadi.assignment.models.AssignmentUser
import com.vm.framework.CurrentUserType
import com.vm.framework.DataStoreWrapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class AssignmentCurrentUser @Inject constructor(dataStoreWrapper: DataStoreWrapper) :
    CurrentUserType<AssignmentUser>(dataStoreWrapper) {

    override val classType: Class<AssignmentUser>
        get() = AssignmentUser::class.java

    override val token: String?
        get() = null
}