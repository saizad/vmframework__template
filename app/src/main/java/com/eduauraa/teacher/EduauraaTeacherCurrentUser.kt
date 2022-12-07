package com.eduauraa.teacher

import com.vm.framework.CurrentUserType
import com.vm.framework.DataStoreWrapper
import com.eduauraa.teacher.models.EduauraaTeacherUser
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class EduauraaTeacherCurrentUser @Inject constructor(dataStoreWrapper: DataStoreWrapper) :
    CurrentUserType<EduauraaTeacherUser>(dataStoreWrapper) {

    override val classType: Class<EduauraaTeacherUser>
        get() = EduauraaTeacherUser::class.java

    override val token: String?
        get() = null
}