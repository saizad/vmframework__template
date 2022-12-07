package com.eduauraa.teacher.components

import com.eduauraa.teacher.di.EduauraaTeacherEnvironment
import com.vm.framework.VmFrameworkNetworkRequest
import com.vm.framework.components.VmFrameworkBaseActivity
import sa.zad.easypermission.PermissionManager
import javax.inject.Inject

abstract class EduauraaTeacherActivity<VM : EduauraaTeacherViewModel> : VmFrameworkBaseActivity<VM>() {

    override fun onSupportNavigateUp() = navController().navigateUp()

    @Inject
    lateinit var eduauraaTeacherEnvironment: EduauraaTeacherEnvironment

    override val networkRequest: VmFrameworkNetworkRequest
        get() = eduauraaTeacherEnvironment.networkRequest

    @Inject
    lateinit var permissionManager: PermissionManager

    override fun permissionManager(): PermissionManager {
        return permissionManager
    }


}