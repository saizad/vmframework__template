package com.drone.destination

import com.drone.destination.models.DroneDestinationAuth
import com.drone.destination.models.DroneDestinationUser
import com.vm.framework.CurrentUserType
import com.vm.framework.DataStoreWrapper
import com.vm.framework.utils.alsoLog
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class DroneDestinationCurrentUser @Inject constructor(private val dataStoreWrapper: DataStoreWrapper) :
    CurrentUserType<DroneDestinationUser>(dataStoreWrapper) {

    override val classType: Class<DroneDestinationUser>
        get() = DroneDestinationUser::class.java

    private var accessToken: String? = null
    private var refreshToken: String? = null

    companion object {
        const val ACCESS_TOKEN_PREF_KEY = "access_token"
        const val REFRESH_TOKEN_PREF_KEY = "refresh_token"
    }

    override val token: String?
        get() = accessToken


    init {
        GlobalScope.launch {
            accessToken = dataStoreWrapper.getValue(ACCESS_TOKEN_PREF_KEY).firstOrNull()
            refreshToken = dataStoreWrapper.getValue(REFRESH_TOKEN_PREF_KEY).firstOrNull()
        }
    }

    suspend fun authLogin(droneDestinationAuth: DroneDestinationAuth) {
        accessToken = droneDestinationAuth.access
        refreshToken = droneDestinationAuth.refresh
        super.login(droneDestinationAuth.user)
        dataStoreWrapper.putValue(REFRESH_TOKEN_PREF_KEY, refreshToken)
        dataStoreWrapper.putValue(ACCESS_TOKEN_PREF_KEY, accessToken)
    }

    fun accessToken(): Flow<String?> {
        return dataStoreWrapper.getValue(ACCESS_TOKEN_PREF_KEY).take(1)
    }

    fun refreshToken(): Flow<String?> {
        return dataStoreWrapper.getValue(REFRESH_TOKEN_PREF_KEY).take(1)
    }
}