package com.agrimguru.ed.di.auth

import com.vm.framework.ActivityResult
import com.vm.framework.Environment
import com.vm.framework.VmFrameworkNetworkRequest
import com.agrimguru.ed.AgrimGuruCurrentUser
import com.agrimguru.ed.api.AuthApi
import kotlinx.coroutines.flow.MutableStateFlow
import sa.zad.easypermission.PermissionManager
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
open class AuthEnvironment @Inject constructor(
    val api: AuthApi,
    currentUser: AgrimGuruCurrentUser,
    networkRequest: VmFrameworkNetworkRequest,
    permissionManager: PermissionManager,
    @Named("nav-result")
    activityResultFlow: MutableStateFlow<ActivityResult<*>>
) : Environment(
    currentUser, networkRequest, permissionManager, activityResultFlow
)