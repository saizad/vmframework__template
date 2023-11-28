package com.drone.destination.components.otp

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.drone.destination.components.BaseViewModel
import com.drone.destination.di.MainEnvironment
import com.drone.destination.models.Token
import com.drone.destination.models.body.PhoneNumberBody
import com.drone.destination.models.body.VerifyOTP
import com.vm.framework.enums.DataState
import com.vm.framework.model.DataModel
import com.vm.framework.utils.successState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.take
import sa.zad.easyretrofit.observables.NeverErrorObservable

abstract class BaseOTPViewModel<R> constructor(
    savedStateHandle: SavedStateHandle,
    environment: MainEnvironment
) : BaseViewModel(environment) {

    private val _tokenFlow = MutableStateFlow<Token?>(null)

    private val tokenFlow =
        merge(savedStateHandle.getLiveData<Token>("token").asFlow(), _tokenFlow.filterNotNull())
            .stateIn(viewModelScope, SharingStarted.Lazily, null)
            .filterNotNull()

    val phoneFlow = savedStateHandle.getLiveData<String>("phone").asFlow().filterNotNull()
    private val phone = savedStateHandle.get<String>("phone")!!

    val formFlow = tokenFlow.map { token ->
        VerifyOTP.Form(token).apply {
            if (environment.isDebugMode) {
                this.autoFill("1111")
            }
        }
    }
        .stateIn(viewModelScope, SharingStarted.Lazily, null)
        .filterNotNull()

    abstract fun requestAPI(verifyOTP: VerifyOTP): NeverErrorObservable<R>

    fun validateOTP(): Flow<DataState<R>> {
        return formFlow.take(1).flatMapLatest {
            networkRequest.flowData(requestAPI(it.requiredBuild()), -1)
        }
    }

    abstract fun requestAPI(phoneNumberBody: PhoneNumberBody): NeverErrorObservable<DataModel<Token>>

    fun resendOTP(): Flow<DataState<DataModel<Token>>> {
        return networkRequest.flowData(requestAPI(PhoneNumberBody(phone)), -1)
            .successState {
                _tokenFlow.value = it.data!!.data
            }
    }
}
