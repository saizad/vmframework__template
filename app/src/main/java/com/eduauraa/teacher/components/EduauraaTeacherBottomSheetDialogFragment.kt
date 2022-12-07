package com.eduauraa.teacher.components

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.eduauraa.teacher.EduauraaTeacherCurrentUser
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.vm.framework.VmFrameworkNetworkRequest
import com.vm.framework.components.VmFrameworkBaseBottomSheetDialogFragment
import com.vm.framework.error.ConnectionErrorData
import sa.zad.easypermission.PermissionManager
import javax.inject.Inject

abstract class EduauraaTeacherBottomSheetDialogFragment<VM : EduauraaTeacherViewModel> :
    VmFrameworkBaseBottomSheetDialogFragment<VM>() {

    val simpleName by lazy {
        javaClass.simpleName.replace("Fragment", "")
    }


    @Inject
    lateinit var currentUser: EduauraaTeacherCurrentUser


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

    fun agrimGuruActivity(): EduauraaTeacherActivity<*> {
        return requireActivity() as EduauraaTeacherActivity<*>
    }

    override fun showConnectionErrorDialog(errorData: ConnectionErrorData) {
        showShortToast("No Internet Connection")
//        SnackBarDialog("No Internet Connection", requireContext(), 4000).show(null)
    }
}