package com.eduauraa.teacher.di

import com.vm.framework.ActivityResult
import com.vm.framework.Environment
import com.vm.framework.VmFrameworkNetworkRequest
import com.eduauraa.teacher.EduauraaTeacherCurrentUser
import com.eduauraa.teacher.api.Api
import kotlinx.coroutines.flow.MutableStateFlow
import sa.zad.easypermission.PermissionManager
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
open class EduauraaTeacherEnvironment @Inject constructor(
    val api: Api,
    currentUser: EduauraaTeacherCurrentUser,
    networkRequest: VmFrameworkNetworkRequest,
    permissionManager: PermissionManager,
    @Named("nav-result")
    activityResultFlow: MutableStateFlow<ActivityResult<*>>
) : Environment(
    currentUser, networkRequest, permissionManager, activityResultFlow
)