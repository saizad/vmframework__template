package com.drone.destination.components

import com.drone.destination.DroneDestinationCurrentUser
import com.drone.destination.di.MainEnvironment
import com.vm.framework.VmFrameworkNetworkRequest
import com.vm.framework.pager.BasePage
import sa.zad.easypermission.PermissionManager
import javax.inject.Inject

abstract class BasePageFragment<VM : BaseViewModel> : BasePage<VM>() {

    @Inject
    lateinit var permissionManager: PermissionManager

    @Inject
    lateinit var mvvmExampleCurrentUser: DroneDestinationCurrentUser

     @Inject
     lateinit var authEnvironment: MainEnvironment

    override fun permissionManager(): PermissionManager {
        return permissionManager
    }

    fun activity(): MainActivity {
        return requireActivity() as MainActivity
    }

     override val networkRequest: VmFrameworkNetworkRequest by lazy {
         authEnvironment.networkRequest
     }
 }