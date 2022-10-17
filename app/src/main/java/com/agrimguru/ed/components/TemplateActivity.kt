package com.agrimguru.ed.components

import com.vm.framework.components.VmFrameworkBaseActivity
import sa.zad.easypermission.PermissionManager
import javax.inject.Inject

abstract class TemplateActivity<VM : AgrimGuruViewModel> : VmFrameworkBaseActivity<VM>() {

    override fun onSupportNavigateUp() = navController().navigateUp()


    @Inject
    lateinit var permissionManager: PermissionManager

    override fun permissionManager(): PermissionManager {
        return permissionManager
    }


}