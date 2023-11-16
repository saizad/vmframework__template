package com.drone.destination.components

import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.drone.destination.DroneDestinationCurrentUser
import com.vm.framework.VmFrameworkNetworkRequest
import com.vm.framework.components.VmFrameworkBaseDialogFragment
import sa.zad.easypermission.PermissionManager
import javax.inject.Inject

abstract class BaseDialogFragment<VM : BaseViewModel> :
    VmFrameworkBaseDialogFragment<VM>() {

    val simpleName by lazy {
        javaClass.simpleName.replace("Dialog Fragment", "")
    }


    @Inject
    override lateinit var networkRequest: VmFrameworkNetworkRequest

    @Inject
    lateinit var currentUser: DroneDestinationCurrentUser

    @Inject
    lateinit var permissionManager: PermissionManager

    override fun permissionManager(): PermissionManager {
        return permissionManager
    }

    override fun onBackPressed(): Boolean {
        return false
    }

    override fun navController(): NavController {
        return findNavController()
    }

    fun activity(): MainActivity {
        return requireActivity() as MainActivity
    }
}