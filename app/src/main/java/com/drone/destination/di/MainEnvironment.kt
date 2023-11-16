package com.drone.destination.di

import com.drone.destination.DroneDestinationCurrentUser
import com.drone.destination.api.Api
import com.vm.framework.ActivityResult
import com.vm.framework.Environment
import com.vm.framework.VmFrameworkNetworkRequest
import kotlinx.coroutines.flow.MutableStateFlow
import sa.zad.easypermission.PermissionManager
import java.util.Locale
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class MainEnvironment @Inject constructor(
    val api: Api,
    locale: MutableStateFlow<Locale>,
    currentUser: DroneDestinationCurrentUser,
    permissionManager: PermissionManager,
    @Named("nav-result")
    activityResultFlow: MutableStateFlow<ActivityResult<*>>,
    networkRequest: VmFrameworkNetworkRequest
) : Environment(locale, currentUser, networkRequest, permissionManager, activityResultFlow)