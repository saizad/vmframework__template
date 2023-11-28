package com.drone.destination.components.signup

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.drone.destination.components.BaseViewModel
import com.drone.destination.di.MainEnvironment
import com.drone.destination.models.Address
import com.drone.destination.models.DroneDestinationUser
import com.drone.destination.models.Token
import com.vm.framework.enums.DataState
import com.vm.framework.model.DataModel
import com.vm.framework.utils.dataStateDataModelSuccessFlow
import com.vm.framework.utils.stateToDataRemoveDataModel
import com.vm.framework.utils.valueFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    environment: MainEnvironment
) : BaseViewModel(environment) {

    private val token = savedStateHandle.get<String>("token")!!

    val form: DroneDestinationUser.Form by lazy {
        if (environment.isDebugMode) {
            DroneDestinationUser.Form(token, DroneDestinationUser.FAKE)
        } else {
            DroneDestinationUser.Form(token)
        }
    }

    val pincodeFlow = form.addressForm.pincodeField.valueFlow
        .filterNotNull()
        .filter { it in 100000..999999 }
        .distinctUntilChanged()
        .stateIn(viewModelScope, SharingStarted.Lazily, null)

    val updateAddressRequest = pincodeFlow
        .filterNotNull()
        .flatMapLatest { pincode ->
            dataStateDataModelSuccessFlow(-1) {
                Address.FAKE.copy(
                    city = listOf(
                        "Mumbai",
                        "Bangalore",
                        "Delhi",
                        "Kolkata",
                        "Chennai"
                    ).random(), pincode = pincode
                )
            }
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, null)
        .filterNotNull()

    val autoPincodeAddress = updateAddressRequest.stateToDataRemoveDataModel()
        .onEach {
            form.addressForm.updatePincodeLocation(it)
        }
        .stateIn(viewModelScope, SharingStarted.Eagerly, null)
        .filterNotNull()

    fun signup(): Flow<DataState<DataModel<Token>>> {
        return networkRequest.flowData(api.signupOTP(form.requiredBuild()), -1)
    }
}
