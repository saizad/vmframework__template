package com.hola.perfectaluminium.di.main

import com.vm.framework.ActivityResult
import com.vm.framework.Environment
import com.vm.framework.VmFrameworkNetworkRequest
import com.hola.perfectaluminium.Template__CurrentUser
import com.hola.perfectaluminium.api.MainApi
import kotlinx.coroutines.flow.MutableStateFlow
import sa.zad.easypermission.PermissionManager
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class MainEnvironment @Inject constructor(
    val api: MainApi,
    currentUser: Template__CurrentUser,
    permissionManager: PermissionManager,
    @Named("nav-result")
    activityResultFlow: MutableStateFlow<ActivityResult<*>>,
    networkRequest: VmFrameworkNetworkRequest
) : Environment(currentUser, networkRequest, permissionManager, activityResultFlow)