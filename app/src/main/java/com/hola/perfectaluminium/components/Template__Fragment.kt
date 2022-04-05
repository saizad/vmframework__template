package com.hola.perfectaluminium.components

import com.vm.framework.components.VmFrameworkBaseFragment
import com.hola.perfectaluminium.Template__CurrentUser
import sa.zad.easypermission.PermissionManager
import javax.inject.Inject

abstract class Template__Fragment<VM : Template__ViewModel> :
    VmFrameworkBaseFragment<VM>() {

    @Inject
    lateinit var currentUserType: Template__CurrentUser

    @Inject
    lateinit var permissionManager: PermissionManager


    override fun permissionManager(): PermissionManager {
        return permissionManager
    }

}