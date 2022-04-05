package com.hola.perfectaluminium.components

import com.vm.framework.pager.BasePage
import com.hola.perfectaluminium.Template__CurrentUser
import sa.zad.easypermission.PermissionManager
import javax.inject.Inject

abstract class Template__PageFragment<VM : Template__ViewModel> : BasePage<VM>() {

    @Inject
    lateinit var permissionManager: PermissionManager

    @Inject
    lateinit var mvvmExampleCurrentUser: Template__CurrentUser

    override fun permissionManager(): PermissionManager {
        return permissionManager
    }

}