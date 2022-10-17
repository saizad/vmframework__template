package com.agrimguru.ed.di.main

import com.vm.framework.ActivityResult
import com.vm.framework.Environment
import com.vm.framework.VmFrameworkNetworkRequest
import com.agrimguru.ed.AgrimGuruCurrentUser
import com.agrimguru.ed.api.MainApi
import kotlinx.coroutines.flow.MutableStateFlow
import sa.zad.easypermission.PermissionManager
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class MainEnvironment @Inject constructor(
    val api: MainApi,
    currentUser: AgrimGuruCurrentUser,
    permissionManager: PermissionManager,
    @Named("nav-result")
    activityResultFlow: MutableStateFlow<ActivityResult<*>>,
    networkRequest: VmFrameworkNetworkRequest
) : Environment(currentUser, networkRequest, permissionManager, activityResultFlow)