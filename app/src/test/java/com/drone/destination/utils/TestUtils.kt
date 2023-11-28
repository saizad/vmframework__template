package com.drone.destination.utils

import com.sa.easyandroidform.StringUtils
import com.sa.easyandroidform.fields.BaseField
import com.sa.easyandroidform.fields.BooleanField
import com.sa.easyandroidform.fields.DoubleField
import com.sa.easyandroidform.fields.FloatField
import com.sa.easyandroidform.fields.IntegerField
import com.sa.easyandroidform.fields.LongField
import com.sa.easyandroidform.fields.MandatoryBooleanField
import com.sa.easyandroidform.fields.MandatoryDoubleField
import com.sa.easyandroidform.fields.MandatoryFloatField
import com.sa.easyandroidform.fields.MandatoryIntegerField
import com.sa.easyandroidform.fields.MandatoryLongField
import com.sa.easyandroidform.fields.MandatoryStringField
import com.sa.easyandroidform.fields.StringField
import com.vm.framework.enums.DataState
import com.vm.framework.error.ApiErrorException
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.mockito.Mockito
import java.util.Random

inline fun <T> T.alsoPrint(tag: String? = null, block: (T) -> String): T {
    println("${tag ?: ""} ${block(this)}")
    return this
}

fun <T> T.alsoPrint(tag: String? = null): T {
    alsoPrint(tag) {
        it.toString()
    }
    return this
}

val <T> Class<T>.mock: T
    get() {
        return Mockito.mock(this)
    }


fun <T> dataStateApiErrorFlow(apiErrorException: ApiErrorException, requestId: Int): Flow<DataState<T>> {
    return flow {
        emit(DataState.Loading(true, requestId))
        delay(10)
        emit(DataState.ApiError(apiErrorException, requestId))
        delay(10)
        emit(DataState.Loading(false, requestId))
    }
}

fun setRandom(field: BaseField<*>?) {
    if (field is IntegerField) {
        field.field = Random().nextInt()
    } else if (field is StringField) {
        field.field = StringUtils.random(10)
    } else if (field is FloatField) {
        field.field = Random().nextFloat()
    } else if (field is DoubleField) {
        field.field = Random().nextDouble()
    } else if (field is BooleanField) {
        field.field = Random().nextBoolean()
    } else if (field is LongField) {
        field.field = Random().nextLong()
    } else if (field is MandatoryDoubleField) {
        field.field = Random().nextDouble()
    } else if (field is MandatoryBooleanField) {
        field.field = Random().nextBoolean()
    } else if (field is MandatoryStringField) {
        field.field = StringUtils.random(10)
    } else if (field is MandatoryFloatField) {
        field.field = Random().nextFloat()
    } else if (field is MandatoryIntegerField) {
        field.field = Random().nextInt()
    } else if (field is MandatoryLongField) {
        field.field = Random().nextLong()
    }
}