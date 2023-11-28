package com.drone.destination.components.phone.signup

import androidx.lifecycle.SavedStateHandle
import com.drone.destination.BuildConfig
import com.drone.destination.components.phone.BasePhoneViewModel
import com.drone.destination.di.MainEnvironment
import com.drone.destination.models.Token
import com.drone.destination.models.body.PhoneNumberBody
import com.drone.destination.models.body.SignupPhoneNumberBody
import com.vm.framework.model.DataModel
import dagger.hilt.android.lifecycle.HiltViewModel
import sa.zad.easyretrofit.observables.NeverErrorObservable
import javax.inject.Inject

@HiltViewModel
class SignupPhoneViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    environment: MainEnvironment
) : BasePhoneViewModel(savedStateHandle, environment) {

    override val form: SignupPhoneNumberBody.Form by lazy {
        if (environment.isDebugMode) {
            SignupPhoneNumberBody.Form(
                PhoneNumberBody.PHONE, SignupPhoneNumberBody(
                    BuildConfig.TEST_USERNAME,
                    privacyPolicyAccepted = true,
                    acceptTermOfUse = true
                )
            )
        } else {
            SignupPhoneNumberBody.Form("")
        }
    }

    override fun buildForm(): PhoneNumberBody {
        return form.requiredBuild()
    }

    override fun requestAPI(phoneNumberBody: PhoneNumberBody): NeverErrorObservable<DataModel<Token>> {
        return api.sendSignupOTP(phoneNumberBody)
    }


}
