package com.eduauraa.teacher.components

import com.vm.framework.pager.BasePage
import com.eduauraa.teacher.EduauraaTeacherCurrentUser
import sa.zad.easypermission.PermissionManager
import javax.inject.Inject

abstract class EduauraaTeacherPageFragment<VM : EduauraaTeacherViewModel> : BasePage<VM>() {

    @Inject
    lateinit var permissionManager: PermissionManager

    @Inject
    lateinit var mvvmExampleCurrentUser: EduauraaTeacherCurrentUser

    override fun permissionManager(): PermissionManager {
        return permissionManager
    }

}