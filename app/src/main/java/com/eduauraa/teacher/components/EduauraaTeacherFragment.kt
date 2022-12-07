package com.eduauraa.teacher.components

import com.eduauraa.teacher.EduauraaTeacherCurrentUser
import com.eduauraa.teacher.di.EduauraaTeacherEnvironment
import com.vm.framework.VmFrameworkNetworkRequest
import com.vm.framework.components.VmFrameworkBaseFragment
import sa.zad.easypermission.PermissionManager
import javax.inject.Inject

abstract class EduauraaTeacherFragment<VM : EduauraaTeacherViewModel> :
    VmFrameworkBaseFragment<VM>() {

    @Inject
    lateinit var environment: EduauraaTeacherEnvironment

    @Inject
    lateinit var currentUserType: EduauraaTeacherCurrentUser

    @Inject
    lateinit var permissionManager: PermissionManager

    override val networkRequest: VmFrameworkNetworkRequest
        get() = environment.networkRequest

    override fun permissionManager(): PermissionManager {
        return permissionManager
    }

}