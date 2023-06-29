package com.shaadi.assignment.di

import com.shaadi.assignment.AssignmentCurrentUser
import com.shaadi.assignment.api.Api
import com.vm.framework.ActivityResult
import com.vm.framework.Environment
import com.vm.framework.VmFrameworkNetworkRequest
import kotlinx.coroutines.flow.MutableStateFlow
import sa.zad.easypermission.PermissionManager
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class MainEnvironment @Inject constructor(
    val api: Api,
    currentUser: AssignmentCurrentUser,
    permissionManager: PermissionManager,
    @Named("nav-result")
    activityResultFlow: MutableStateFlow<ActivityResult<*>>,
    networkRequest: VmFrameworkNetworkRequest
) : Environment(currentUser, networkRequest, permissionManager, activityResultFlow)