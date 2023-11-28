package com.drone.destination.api

import com.drone.destination.models.DroneDestinationAuth
import com.drone.destination.models.DroneDestinationUser
import com.drone.destination.models.Token
import com.drone.destination.models.body.PhoneNumberBody
import com.drone.destination.models.body.RefreshBody
import com.drone.destination.models.body.VerifyOTP
import com.vm.framework.model.DataModel
import retrofit2.http.Body
import retrofit2.http.POST
import sa.zad.easyretrofit.observables.NeverErrorObservable

interface Api {
    @POST("auth/refresh/token/")
    fun refresh(@Body refreshBody: RefreshBody): NeverErrorObservable<DataModel<DroneDestinationAuth>>

    @POST("auth/login/otp/send/")
    fun sendLoginOTP(@Body phoneNumberBody: PhoneNumberBody): NeverErrorObservable<DataModel<Token>>

    @POST("auth/login/otp/validate/")
    fun validateLoginOTP(@Body verifyOTP: VerifyOTP): NeverErrorObservable<DataModel<DroneDestinationAuth>>

    @POST("auth/signup/otp/send/")
    fun sendSignupOTP(@Body phoneNumberBody: PhoneNumberBody): NeverErrorObservable<DataModel<Token>>

    @POST("auth/signup/otp/validate/")
    fun validateSignupOTP(@Body verifyOTP: VerifyOTP): NeverErrorObservable<DataModel<Token>>

    @POST("auth/signup/")
    fun signupOTP(@Body droneDestinationUser: DroneDestinationUser): NeverErrorObservable<DataModel<Token>>
}
