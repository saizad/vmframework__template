package com.eduauraa.teacher.components

import com.eduauraa.teacher.EduauraaTeacherCurrentUser
import com.vm.framework.Environment
import com.vm.framework.VmFrameworkNetworkRequest
import com.vm.framework.components.VmFrameworkBaseViewModel

abstract class EduauraaTeacherViewModel(
    environment: Environment
) : VmFrameworkBaseViewModel(environment){

    val currentUser: EduauraaTeacherCurrentUser get() = environment.currentUser as EduauraaTeacherCurrentUser
}