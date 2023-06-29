package com.shaadi.assignment.components

import com.shaadi.assignment.AssignmentCurrentUser
import com.shaadi.assignment.di.MainEnvironment
import com.vm.framework.VmFrameworkNetworkRequest
import com.vm.framework.components.VmFrameworkBaseFragment
import sa.zad.easypermission.PermissionManager
import javax.inject.Inject

abstract class AssignmentFragment<VM : AssignmentViewModel> :
    VmFrameworkBaseFragment<VM>() {

    @Inject
    lateinit var environment: MainEnvironment

    @Inject
    lateinit var currentUserType: AssignmentCurrentUser

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