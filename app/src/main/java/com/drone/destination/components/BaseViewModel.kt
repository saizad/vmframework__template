package com.drone.destination.components

import com.drone.destination.DroneDestinationCurrentUser
import com.vm.framework.Environment
import com.vm.framework.components.VmFrameworkBaseViewModel

open class BaseViewModel(
    environment: Environment
) : VmFrameworkBaseViewModel(environment){

    val currentUser: DroneDestinationCurrentUser get() = environment.currentUser as DroneDestinationCurrentUser
}