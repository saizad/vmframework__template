package com.drone.destination.components.phone.login

import androidx.lifecycle.SavedStateHandle
import com.drone.destination.BuildConfig
import com.drone.destination.components.phone.BasePhoneViewModel
import com.drone.destination.di.MainEnvironment
import com.drone.destination.models.Token
import com.drone.destination.models.body.PhoneNumberBody
import com.vm.framework.model.DataModel
import dagger.hilt.android.lifecycle.HiltViewModel
import sa.zad.easyretrofit.observables.NeverErrorObservable
import javax.inject.Inject

@HiltViewModel
class LoginPhoneViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    environment: MainEnvironment
) : BasePhoneViewModel(savedStateHandle, environment) {

    override val form: PhoneNumberBody.Form by lazy {
        if (environment.isDebugMode) {
            PhoneNumberBody.Form(PhoneNumberBody.PHONE, PhoneNumberBody(BuildConfig.TEST_USERNAME))
        } else {
            PhoneNumberBody.Form("")
        }
    }

    override fun buildForm(): PhoneNumberBody {
        return form.requiredBuild()
    }

    override fun requestAPI(phoneNumberBody: PhoneNumberBody): NeverErrorObservable<DataModel<Token>> {
        return api.sendLoginOTP(phoneNumberBody)
    }


}
