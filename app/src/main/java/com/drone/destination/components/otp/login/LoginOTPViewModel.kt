package com.drone.destination.components.otp.login

import androidx.lifecycle.SavedStateHandle
import com.drone.destination.components.otp.BaseOTPViewModel
import com.drone.destination.di.MainEnvironment
import com.drone.destination.models.DroneDestinationAuth
import com.drone.destination.models.Token
import com.drone.destination.models.body.PhoneNumberBody
import com.drone.destination.models.body.VerifyOTP
import com.vm.framework.model.DataModel
import dagger.hilt.android.lifecycle.HiltViewModel
import sa.zad.easyretrofit.observables.NeverErrorObservable
import javax.inject.Inject

@HiltViewModel
class LoginOTPViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    environment: MainEnvironment
) : BaseOTPViewModel<DataModel<DroneDestinationAuth>>(savedStateHandle, environment) {
    override fun requestAPI(verifyOTP: VerifyOTP): NeverErrorObservable<DataModel<DroneDestinationAuth>> {
        return api.validateLoginOTP(verifyOTP)
    }

    override fun requestAPI(phoneNumberBody: PhoneNumberBody): NeverErrorObservable<DataModel<Token>> {
        return api.sendLoginOTP(phoneNumberBody)
    }
}
