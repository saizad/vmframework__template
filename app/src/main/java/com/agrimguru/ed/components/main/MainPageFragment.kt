package com.agrimguru.ed.components.main

import com.vm.framework.VmFrameworkNetworkRequest
import com.vm.framework.pager.BasePage
import com.agrimguru.ed.AgrimGuruCurrentUser
import com.agrimguru.ed.di.main.MainEnvironment
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
    lateinit var mvvmExampleCurrentUser: AgrimGuruCurrentUser

    override fun permissionManager(): PermissionManager {
        return permissionManager
    }

}