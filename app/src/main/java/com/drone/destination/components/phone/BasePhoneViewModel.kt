package com.drone.destination.components.phone

import androidx.lifecycle.SavedStateHandle
import com.drone.destination.components.BaseViewModel
import com.drone.destination.di.MainEnvironment
import com.drone.destination.models.Token
import com.drone.destination.models.body.PhoneNumberBody
import com.vm.framework.enums.DataState
import com.vm.framework.model.DataModel
import kotlinx.coroutines.flow.Flow
import sa.zad.easyretrofit.observables.NeverErrorObservable

abstract class BasePhoneViewModel constructor(
    savedStateHandle: SavedStateHandle,
    environment: MainEnvironment
) : BaseViewModel(environment) {

    abstract val form: PhoneNumberBody.Form

    abstract fun buildForm(): PhoneNumberBody

    abstract fun requestAPI(phoneNumberBody: PhoneNumberBody): NeverErrorObservable<DataModel<Token>>

    fun sendOTP(): Flow<DataState<DataModel<Token>>> {
        return networkRequest.flowData(requestAPI(buildForm()), -1)
    }
}
