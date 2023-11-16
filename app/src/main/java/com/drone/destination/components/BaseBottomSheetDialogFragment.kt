package com.drone.destination.components

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.drone.destination.DroneDestinationCurrentUser
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.vm.framework.VmFrameworkNetworkRequest
import com.vm.framework.components.VmFrameworkBaseBottomSheetDialogFragment
import com.vm.framework.error.ConnectionErrorData
import sa.zad.easypermission.PermissionManager
import javax.inject.Inject

abstract class BaseBottomSheetDialogFragment<VM : BaseViewModel> :
    VmFrameworkBaseBottomSheetDialogFragment<VM>() {

    val simpleName by lazy {
        javaClass.simpleName.replace("Fragment", "")
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

    @SuppressLint("RestrictedApi")
    override fun onCreateDialog(savedInstanceState: Bundle?): BottomSheetDialog {
        val bottomSheetDialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        bottomSheetDialog.behavior.disableShapeAnimations()
        return bottomSheetDialog
    }

    fun activity(): MainActivity {
        return requireActivity() as MainActivity
    }

    override fun showConnectionErrorDialog(errorData: ConnectionErrorData) {
        showShortToast("No Internet Connection")
//        SnackBarDialog("No Internet Connection", requireContext(), 4000).show(null)
    }
}