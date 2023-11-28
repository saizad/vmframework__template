package com.drone.destination.components.splash

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.drone.destination.components.BaseViewModel
import com.drone.destination.di.MainEnvironment
import com.drone.destination.models.DroneDestinationAuth
import com.drone.destination.models.DroneDestinationUser
import com.drone.destination.models.Token
import com.drone.destination.models.body.RefreshBody
import com.drone.destination.utils.alsoPrint
import com.drone.destination.utils.loginExpired
import com.vm.framework.utils.alsoLog
import com.vm.framework.utils.dataStateDataModelSuccessFlow
import com.vm.framework.utils.dataStateErrorFlow
import com.vm.framework.utils.failed
import com.vm.framework.utils.stateToDataRemoveDataModel
import com.vm.framework.utils.successMap
import com.vm.framework.utils.successState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    environment: MainEnvironment
) : BaseViewModel(environment) {

    private val refreshRequest = currentUser.refreshToken()
        .flatMapLatest {
            networkRequest.flowData(
                api.refresh(RefreshBody(it.toString())),
                -1
            )
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, null)
        .filterNotNull()


    val loginExpired = refreshRequest.loginExpired

    val validUserRequest = currentUser.currentUserFlow
        .flatMapLatest { user ->
            if (user != null) {
                refreshRequest
            } else {
                dataStateErrorFlow(Throwable("User not found"), 1)
            }
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, null)
        .filterNotNull()

    val validUser = validUserRequest.successState().successMap { it!!.data.user }
        .stateIn(viewModelScope, SharingStarted.Lazily, null)
        .filterNotNull()

    val autoLoginUpdate = validUserRequest
        .stateToDataRemoveDataModel()
        .onEach { currentUser.authLogin(it) }
        .stateIn(viewModelScope, SharingStarted.Eagerly, null)

}
