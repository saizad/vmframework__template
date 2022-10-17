package com.agrimguru.ed.components

import com.vm.framework.pager.BasePage
import com.agrimguru.ed.AgrimGuruCurrentUser
import sa.zad.easypermission.PermissionManager
import javax.inject.Inject

abstract class AgrimGuruPageFragment<VM : AgrimGuruViewModel> : BasePage<VM>() {

    @Inject
    lateinit var permissionManager: PermissionManager

    @Inject
    lateinit var mvvmExampleCurrentUser: AgrimGuruCurrentUser

    override fun permissionManager(): PermissionManager {
        return permissionManager
    }

}