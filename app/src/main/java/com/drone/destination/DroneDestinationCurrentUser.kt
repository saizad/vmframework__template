package com.drone.destination

import com.drone.destination.models.DroneDestinationUser
import com.vm.framework.CurrentUserType
import com.vm.framework.DataStoreWrapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class DroneDestinationCurrentUser @Inject constructor(dataStoreWrapper: DataStoreWrapper) :
    CurrentUserType<DroneDestinationUser>(dataStoreWrapper) {

    override val classType: Class<DroneDestinationUser>
        get() = DroneDestinationUser::class.java

    override val token: String?
        get() = null
}