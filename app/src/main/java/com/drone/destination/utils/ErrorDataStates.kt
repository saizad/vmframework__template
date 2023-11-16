package com.drone.destination.utils

import androidx.annotation.DrawableRes
import androidx.core.view.forEach
import androidx.core.view.isVisible
import com.drone.destination.R
import com.drone.destination.databinding.LayoutStatesBinding
import com.vm.framework.enums.DataState
import com.vm.framework.model.DataModel
import com.vm.framework.model.ErrorModel
import com.vm.framework.model.PageDataModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.combineTransform
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.runningFold
import sa.zad.easyretrofit.ResponseException
import java.net.ConnectException


data class AllDataState<R>(
    var loading: DataState.Loading?,
    var error: DataState.Error?,
    var apiError: DataState.ApiError?,
    var success: DataState.Success<R>?,
    val requestId: Int
) {

    val hasFailed: Boolean
        get() {
            return error != null || apiError != null
        }

    val anyError: DataState.Error?
        get() {
            return if (apiError != null) {
                DataState.Error(apiError!!.apiErrorException, requestId)
            } else if (error != null) {
                error
            } else {
                null
            }
        }

    val requestCompleted: Boolean
        get() {
            return success != null && loading?.isLoading == false
        }
}


fun LayoutStatesBinding.loading(isLoading: Boolean) {
    root.isVisible = isLoading
    root.forEach {
        it.isVisible = false
    }
    this.loading.isVisible = isLoading
}

fun LayoutStatesBinding.bind(
    @DrawableRes res: Int? = null,
    title: String? = null,
    description: String? = null,
    show: Boolean = true,
    isLoading: Boolean = false,
) {
    res?.let {
        stateImage.setImageResource(res)
    }
    stateImage.isVisible = res != null
    this.loading.isVisible = false
    this.title.text = title
    this.description.text = description

    this.title.isVisible = title != null
    this.description.isVisible = description != null
    root.isVisible = show
}

//WIP
private suspend fun <T> Iterable<Flow<AllDataState<DataModel<List<T>>>>>.loading(state: LayoutStatesBinding) {
    combine(this.map { it.map { it.loading?.isLoading == true } }) { it }.map { it.all { it } }
        .collectLatest {
            state.bind(isLoading = it, description = "adfasdf")
        }
}

suspend fun <T> Iterable<Flow<AllDataState<DataModel<List<T>>>>>.emptyListFlows(state: LayoutStatesBinding) {
    combine(this.map { it.shouldShowEmptyListState() }) { it }.map { it.all { it } }.collectLatest {

    }
}

suspend fun Iterable<Flow<Pair<Boolean, Throwable?>>>.notFound(state: LayoutStatesBinding) {
    combineFlows().map { it.anyError }.collectLatest {

    }
}

suspend fun Iterable<Flow<Pair<Boolean, Throwable?>>>.anyError(state: LayoutStatesBinding) {
    combineFlows().anyError(state)
}

suspend fun Iterable<Flow<Pair<Boolean, ErrorModel?>>>.apiError(state: LayoutStatesBinding) {
    combineFlows().apiError(state)
}

private fun <T> Iterable<Flow<Pair<Boolean, T?>>>.combineFlows(): Flow<Array<Pair<Boolean, T?>>> {
    return combine(this) { it }
}

suspend fun Flow<Array<Pair<Boolean, ErrorModel?>>>.apiError(state: LayoutStatesBinding) {
    return map { it.apiError }.somethingWentWrong(state)
}

suspend fun Flow<Array<Pair<Boolean, Throwable?>>>.anyError(state: LayoutStatesBinding) {
    return map { it.anyError }.somethingWentWrong(state)
}

suspend fun Flow<Triple<String?, String?, Boolean>>.somethingWentWrong(state: LayoutStatesBinding) {
    return collectLatest {

    }
}

val List<ErrorModel?>.fields: String?
    get() {
        return if (all { it != null }) {
            filterNotNull().map { it.fields ?: it.error.description }.joinToString("\n") { it }
        } else {
            null
        }
    }

val List<ErrorModel?>.error: String?
    get() {
        return if (all { it != null }) {
            filterNotNull().map { it.error.error }.joinToString(" -- ") { it }
        } else {
            null
        }
    }

val Array<Pair<Boolean, ErrorModel?>>.apiError: Triple<String?, String?, Boolean>
    get() {
        val title = map { it.second }.error
        val description = map { it.second }.fields
        return Triple(title, description, show)
    }

val Array<Pair<Boolean, Throwable?>>.anyError: Triple<String?, String?, Boolean>
    get() {
        val description =
            map { it.second?.message }.distinct().filterNotNull().joinToString("\n") { it }
        return Triple(null, description, show)
    }

val Flow<Pair<Boolean, ResponseException?>>.filterOut404: Flow<Pair<Boolean, ResponseException?>>
    get() {
        return filter { it.second?.code() != 404 }
    }

val ErrorModel.fields: String?
    get() = error.fields?.joinToString("\n") {
        "${it.field} : ${
            it.message?.joinToString { it }
        }"
    }


val Pair<ErrorModel, ErrorModel>.fields: String
    get() {
        val sb = StringBuilder()
        first.fields?.let {
            sb.append(it)
        }
        second.fields?.let {
            sb.append(it)
        }
        return sb.toString()
    }

fun <T> Flow<List<AllDataState<T>>>.connectExceptionState(action: suspend (ConnectException) -> Unit): Flow<List<AllDataState<T>>> {
    return states {
        val isNotFound =
            it.all { it.loading?.isLoading == false && it.error?.throwable is ConnectException }
        if (isNotFound) {
            action(
                it.map { it.error?.throwable as? ConnectException }.firstOrNull()
                    ?: ConnectException("")
            )
        }
        isNotFound
    }
}

fun <T> Flow<List<AllDataState<T>>>.notFoundErrorState(action: suspend () -> Unit): Flow<List<AllDataState<T>>> {
    return states {
        val isNotFound = it.all { it.loading?.isLoading == false && it.isNotFound }
        if (isNotFound) {
            action()
        }
        isNotFound
    }
}

fun <T> Flow<List<AllDataState<T>>>.apiErrorState(action: suspend (List<ErrorModel>) -> Unit): Flow<List<AllDataState<T>>> {
    return states {
        val isApiError = it.all { it.loading?.isLoading == false && it.apiError != null }
        if (isApiError) {
            action(it.map { it.apiError!!.apiErrorException.errorModel as ErrorModel })
        }
        isApiError
    }
}

val List<AllDataState<*>>.anyError: String
    get() {
        return joinToString("\n") {
            it.anyError?.throwable?.message ?: "NA"
        }
    }

fun <T> Flow<List<AllDataState<T>>>.anyErrorState(action: suspend (Throwable) -> Unit): Flow<List<AllDataState<T>>> {
    return states {
        val isAnyError = it.all { it.loading?.isLoading == false && it.anyError != null }
        if (isAnyError) {
            action(Throwable(it.anyError))
        }
        isAnyError
    }
}


fun <T> Flow<List<AllDataState<T>>>.loadingState(action: suspend (Boolean) -> Unit): Flow<List<AllDataState<T>>> {
    return states {
        val loading = it.any { it.loading?.isLoading == true }
        action(loading)
        loading
    }
}

fun <T> Flow<List<AllDataState<DataModel<List<T>>>>>.emptyListState(action: suspend (Boolean) -> Unit): Flow<List<AllDataState<DataModel<List<T>>>>> {
    return states {
        val isEmpty =
            it.all { it.loading?.isLoading == false && (it.success?.data?.data?.isEmpty() == true || it.success == null) }
        action(isEmpty)
        isEmpty
    }
}

fun <T> Flow<List<AllDataState<PageDataModel<T, *>>>>.emptyPageState(action: suspend (Boolean) -> Unit): Flow<List<AllDataState<PageDataModel<T, *>>>> {
    return states {
        val isEmpty =
            it.all { it.loading?.isLoading == false && (it.success?.data?.data?.isEmpty() == true || it.success == null) }
        action(isEmpty)
        isEmpty
    }
}

fun <T> multi(list: List<Flow<AllDataState<T>>>): Flow<List<AllDataState<T>>> {
    return combineTransform(list.toList()) {
        emit(it.toList())
    }
}

fun <T> Flow<List<AllDataState<T>>>.states(predicate: suspend (List<AllDataState<T>>) -> Boolean): Flow<List<AllDataState<T>>> {
    return flatMapLatest {
        if (predicate(it)) {
            emptyFlow()
        } else {
            flowOf(it)
        }
    }
}

fun <T> Flow<DataState<T>>.allStates(): Flow<AllDataState<T>> {
    val d = AllDataState<T>(null, null, null, null, -1)
    return runningFold(d) { acc, value ->
        when (value) {
            is DataState.Success -> {
                acc.copy(success = value)
            }

            is DataState.ApiError -> {
                acc.copy(apiError = value, error = null)
            }

            is DataState.Error -> {
                acc.copy(apiError = null, error = value)
            }

            is DataState.Loading -> {
                if (!value.isLoading) {
                    acc.copy(loading = value)
                } else {
                    AllDataState(loading = value, null, null, null, value.requestId)
                }
            }
        }
    }
}

val <T> AllDataState<T>.responseException: ResponseException?
    get() {
        return error?.throwable as? ResponseException
    }

val <T> AllDataState<T>.isNotFound: Boolean
    get() {
        return responseException?.code() == 404
    }

fun <T> Flow<AllDataState<DataModel<List<T>>>>.shouldShowEmptyListState(): Flow<Boolean> {
    return filter { it.success != null || it.loading?.isLoading == true }.map {
        !(it.loading?.isLoading ?: true) && it.success?.data?.data?.isEmpty() ?: true
    }
}

val <T> Flow<AllDataState<DataModel<List<T>>>>.stateList: Flow<List<T>>
    get() {
        return filter { !it.hasFailed && it.loading != null }.flatMapLatest {
            if (it.loading?.isLoading == true) {
                flowOf(emptyList())
            } else {
                flowOf(it.success!!.data!!.data)
            }
        }
    }


val <T>  Array<Pair<Boolean, T?>>.show: Boolean
    get() {
        return all { it.show }
    }

val <T>  Pair<Boolean, T?>.show: Boolean
    get() {
        return (!first && second != null)
    }

fun <T> Flow<AllDataState<T>>.isRequestCompetedWithSuccess(): Flow<Boolean> {
    return map { it.requestCompleted }
}

fun <T> Flow<AllDataState<T>>.requestCompetedWithSuccess(): Flow<AllDataState<T>> {
    return filter { it.requestCompleted }
}
