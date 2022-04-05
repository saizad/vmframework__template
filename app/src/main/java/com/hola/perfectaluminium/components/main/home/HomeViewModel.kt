package com.hola.perfectaluminium.components.main.home

import com.vm.framework.enums.DataState
import com.vm.framework.model.DataModel
import com.hola.perfectaluminium.ApiRequestCodes.DELAYED_RESPONSE
import com.hola.perfectaluminium.ApiRequestCodes.DELETE_USER
import com.hola.perfectaluminium.ApiRequestCodes.RANDOM_REQUEST
import com.hola.perfectaluminium.ApiRequestCodes.RESOURCE_NOT_FOUND
import com.hola.perfectaluminium.RequestCodes
import com.hola.perfectaluminium.components.main.MainViewModel
import com.hola.perfectaluminium.di.main.MainEnvironment
import com.hola.perfectaluminium.models.Template__User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
open class HomeViewModel @Inject constructor(
    environment: MainEnvironment
) : MainViewModel(environment) {


    val v = environment.activityResultFlow
        .filter { it.isOk && it.isRequestCode(RequestCodes.USER) }
        .map { it.value!! }
        .filterIsInstance<Template__User>()
        .take(1)


   open fun delayed(
        delay: Int,
        requestId: Int = DELAYED_RESPONSE
    ): Flow<DataState<DataModel<List<Template__User>>>> {
        return flowData(api.delayedResponse(delay), requestId)
    }

    open fun resourceNotFound(requestId: Int = RESOURCE_NOT_FOUND): Flow<DataState<Void>> {
        return flowData(api.resourceNotFound(), requestId)
    }

    open fun noContentResponse(requestId: Int = DELETE_USER): Flow<DataState<Void>> {
        return flowData(api.noContentResponse(), requestId)
    }

//    override fun showError(apiErrorData: ApiErrorData) {
//        if(!apiErrorData.isThisRequest(RESOURCE_NOT_FOUND)) {
//            super.showError(apiErrorData)
//        }
//    }

    override fun showLoading(loadingData: LoadingData) {
        if(!loadingData.isThisRequest(RANDOM_REQUEST)) {
            super.showLoading(loadingData)
        }
    }
}