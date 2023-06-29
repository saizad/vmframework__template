package com.shaadi.assignment.api

import com.shaadi.assignment.models.ResultsModel
import com.shaadi.assignment.models.ShaadiUser
import retrofit2.http.GET
import retrofit2.http.Query
import sa.zad.easyretrofit.observables.NeverErrorObservable

interface Api {

    @GET("https://randomuser.me/api/")
    fun randomUsers(@Query("results") count: Int = 10): NeverErrorObservable<ResultsModel<List<ShaadiUser>>>

}
