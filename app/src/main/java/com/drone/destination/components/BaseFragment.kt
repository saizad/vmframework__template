package com.drone.destination.components

import com.drone.destination.DroneDestinationCurrentUser
import com.drone.destination.di.MainEnvironment
import com.vm.framework.VmFrameworkNetworkRequest
import com.vm.framework.components.VmFrameworkBaseFragment
import sa.zad.easypermission.PermissionManager
import javax.inject.Inject

abstract class BaseFragment<VM : BaseViewModel> :
    VmFrameworkBaseFragment<VM>() {

    @Inject
    lateinit var environment: MainEnvironment

    @Inject
    lateinit var currentUserType: DroneDestinationCurrentUser

    @Inject
    lateinit var permissionManager: PermissionManager

    override val networkRequest: VmFrameworkNetworkRequest
        get() = environment.networkRequest

    override fun permissionManager(): PermissionManager {
        return permissionManager
    }

    fun activity(): MainActivity {
        return requireActivity() as MainActivity
    }

}