package com.hola.perfectaluminium.api

import com.vm.framework.model.DataModel
import com.vm.framework.model.IntPageDataModel
import com.hola.perfectaluminium.models.Template__User
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Query
import sa.zad.easyretrofit.observables.NeverErrorObservable

interface MainApi {

    @GET("api/users/")
    fun users(@Query("page") page: Int?): NeverErrorObservable<IntPageDataModel<Template__User>>

    @GET("api/users/")
    fun delayedResponse(@Query("delay") delaySecs: Int): NeverErrorObservable<DataModel<List<Template__User>>>

    @GET("/api/unknown/23")
    fun resourceNotFound(): NeverErrorObservable<Void>

    @DELETE("/api/users/2")
    fun noContentResponse(): NeverErrorObservable<Void>
}