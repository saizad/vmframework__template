package com.drone.destination.components

import com.drone.destination.DroneDestinationCurrentUser
import com.drone.destination.di.MainEnvironment
import com.vm.framework.components.VmFrameworkBaseViewModel

open class BaseViewModel(
    environment: MainEnvironment
) : VmFrameworkBaseViewModel(environment){

    val currentUser: DroneDestinationCurrentUser get() = environment.currentUser as DroneDestinationCurrentUser

    val api = environment.api
}