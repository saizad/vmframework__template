package com.agrimguru.ed.components

import com.vm.framework.components.VmFrameworkBaseFragment
import com.agrimguru.ed.AgrimGuruCurrentUser
import sa.zad.easypermission.PermissionManager
import javax.inject.Inject

abstract class AgrimGuruFragment<VM : AgrimGuruViewModel> :
    VmFrameworkBaseFragment<VM>() {

    @Inject
    lateinit var currentUserType: AgrimGuruCurrentUser

    @Inject
    lateinit var permissionManager: PermissionManager


    override fun permissionManager(): PermissionManager {
        return permissionManager
    }

}