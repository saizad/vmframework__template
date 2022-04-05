package com.hola.perfectaluminium.components

import com.google.gson.Gson
import com.vm.framework.Environment
import com.vm.framework.components.VmFrameworkBaseViewModel
import com.vm.framework.enums.DataState
import com.hola.perfectaluminium.Template__CurrentUser
import com.hola.perfectaluminium.models.ApiError
import kotlinx.coroutines.flow.Flow
import sa.zad.easyretrofit.observables.NeverErrorObservable
import javax.inject.Inject

abstract class Template__ViewModel(
    environment: Environment
) : VmFrameworkBaseViewModel(environment){

    @Inject
    lateinit var gson: Gson

    fun <M> flowData(
        observable: NeverErrorObservable<M>,
        requestId: Int
    ): Flow<DataState<M>> {
        return networkRequest.flowData(observable, requestId, ApiError::class.java)
    }

    fun currentUser(): Template__CurrentUser {
        return environment.currentUser as Template__CurrentUser
    }
}