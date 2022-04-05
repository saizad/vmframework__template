package com.hola.perfectaluminium.components.main

import com.vm.framework.VmFrameworkNetworkRequest
import com.vm.framework.pager.BasePage
import com.hola.perfectaluminium.Template__CurrentUser
import com.hola.perfectaluminium.di.main.MainEnvironment
import sa.zad.easypermission.PermissionManager
import javax.inject.Inject

abstract class MainPageFragment<VM : MainPageViewModel> : BasePage<VM>() {

    @Inject
    lateinit var mainEnvironment: MainEnvironment

    override val networkRequest: VmFrameworkNetworkRequest by lazy {
        mainEnvironment.networkRequest
    }

    @Inject
    lateinit var permissionManager: PermissionManager

    @Inject
    lateinit var mvvmExampleCurrentUser: Template__CurrentUser

    override fun permissionManager(): PermissionManager {
        return permissionManager
    }

}