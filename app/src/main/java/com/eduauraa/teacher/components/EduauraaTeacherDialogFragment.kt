package com.eduauraa.teacher.components

import android.annotation.SuppressLint
import android.os.Bundle
import com.eduauraa.teacher.EduauraaTeacherCurrentUser
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.vm.framework.VmFrameworkNetworkRequest
import com.vm.framework.components.VmFrameworkBaseDialogFragment
import sa.zad.easypermission.PermissionManager
import javax.inject.Inject

abstract class EduauraaTeacherDialogFragment<VM : EduauraaTeacherViewModel> :
    VmFrameworkBaseDialogFragment<VM>() {

    @Inject
    lateinit var eduauraaTeacherCurrentUser: EduauraaTeacherCurrentUser

    @Inject
    lateinit var permissionManager: PermissionManager

    @Inject
    lateinit var agrimguruNetworkRequest: VmFrameworkNetworkRequest

    override val networkRequest: VmFrameworkNetworkRequest = agrimguruNetworkRequest

    override fun permissionManager(): PermissionManager {
        return permissionManager
    }

    override fun onBackPressed(): Boolean {
        return false
    }

    @SuppressLint("RestrictedApi")
    override fun onCreateDialog(savedInstanceState: Bundle?): BottomSheetDialog {
        val bottomSheetDialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        return bottomSheetDialog
    }

    fun agrimGuruActivity(): EduauraaTeacherActivity<*> {
        return requireActivity() as EduauraaTeacherActivity<*>
    }
}