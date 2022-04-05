package com.hola.perfectaluminium.api

import com.vm.framework.model.DataModel
import com.vm.framework.model.LoginBody
import com.hola.perfectaluminium.models.LoginResponse
import com.hola.perfectaluminium.models.Template__User
import retrofit2.http.*
import sa.zad.easyretrofit.observables.NeverErrorObservable

interface AuthApi {

    @POST("api/login/")
    fun login(@Body loginBody: LoginBody): NeverErrorObservable<LoginResponse>

    @GET("api/users/{user}")
    fun user(@Path("user") user: Int): NeverErrorObservable<DataModel<Template__User>>

}