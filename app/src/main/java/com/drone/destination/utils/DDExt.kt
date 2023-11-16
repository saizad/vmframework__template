package com.drone.destination.utils

import android.app.LocaleManager
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.os.Build
import android.os.LocaleList
import android.view.MenuItem
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.core.os.LocaleListCompat
import com.drone.destination.ItemOptionClickFields
import com.drone.destination.components.BaseBottomSheetDialogFragment
import com.drone.destination.components.BaseDialogFragment
import com.drone.destination.components.BaseFragment
import com.drone.destination.components.BasePageFragment
import com.drone.destination.components.MainActivity
import com.drone.destination.models.DDPageDataModel
import com.google.android.gms.tasks.Task
import com.google.firebase.messaging.FirebaseMessaging
import com.sa.easyandroidform.ObjectUtils
import com.sa.easyandroidform.StringUtils
import com.vm.framework.enums.DataState
import com.vm.framework.utils.Utils.ordinal
import com.vm.framework.utils.loadingState
import com.vm.framework.utils.onError
import io.reactivex.exceptions.CompositeException
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import sa.zad.pagedrecyclerlist.ConstraintLayoutItem
import sa.zad.pagedrecyclerlist.ConstraintLayoutList
import java.io.File
import java.text.NumberFormat
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern


fun isPhoneValid(phone: String?): Boolean {

    val pattern =
        "^\\s*(?:[0])?([1-9])([0-9]{2})(\\d{3})(\\d{4})(?: *x(\\d+))?\\s*$"

    return if (!phone.isNullOrBlank()) {
        Pattern.matches(pattern, phone)
    } else {
        false
    }
}

fun shareApplication(context: Context, shareMessage: String?) {
    val shareIntent = Intent(Intent.ACTION_SEND)
    shareIntent.type = "text/plain"
    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name")
    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
    context.startActivity(Intent.createChooser(shareIntent, "choose one"))
}

fun String.name(firstName: Boolean = true): String {
    val split =
        StringUtils.stripTrailingLeadingNewLines(this)
            .split(" ".toRegex()).toTypedArray()
    return if (!firstName) {
        split.filterIndexed { index, s -> index >= 1 }.joinToString(separator = " ") { it }
    } else {
        split[0]
    }
}

@Throws(CompositeException::class)
fun isFullNameValid(name: String) {
    if (!isFirstNameValid(name.name())) {
        throw CompositeException(Exception("First name can only contain combination of alpha characters, -, ' and :"))
    }

    if (!isLastNameValid(name.name(false))) {
        throw CompositeException(Exception("Last name can only contain combination of alpha characters, spaces, -, ' and :"))
    }
}

fun isFirstNameValid(name: String?): Boolean {
    return ObjectUtils.isNotNull(name) && name!!.matches("^[A-Za-z':-]+$".toRegex())
}

fun isLastNameValid(name: String?): Boolean {
    return ObjectUtils.isNotNull(name) && name!!.matches("^[A-Za-z':+\\s-]+$".toRegex())
}

fun <T> Flow<DataState<T>>.showLoadingDialog(fragment: MainActivity): Flow<DataState<T>> {
    return _showLoading(fragment)
}

fun <T> Flow<DataState<T>>.showLoadingDialog(fragment: BaseFragment<*>): Flow<DataState<T>> {
    return _showLoading(fragment.activity())
}

fun <T> Flow<DataState<T>>.showLoadingDialog(fragment: BasePageFragment<*>): Flow<DataState<T>> {
    return _showLoading(fragment.activity())
}

fun <T> Flow<DataState<T>>.showLoadingDialog(fragment: BaseDialogFragment<*>): Flow<DataState<T>> {
    return _showLoading(fragment.activity())
}

fun <T> Flow<DataState<T>>.showLoadingDialog(fragment: BaseBottomSheetDialogFragment<*>): Flow<DataState<T>> {
    return _showLoading(fragment.activity())
}

fun <T> Flow<DataState<T>>._showLoading(activity: MainActivity): Flow<DataState<T>> {
    return loadingState {
        activity.showApiRequestLoadingDialog(it.isLoading)
    }
}

fun <T> Flow<DataState<T>>.showToastError(activity: MainActivity): Flow<DataState<T>> {
    return toastError(activity)
}

fun <T> Flow<DataState<T>>.showToastError(fragment: BaseFragment<*>): Flow<DataState<T>> {
    return toastError(fragment.activity())
}

fun <T> Flow<DataState<T>>.showToastError(fragment: BasePageFragment<*>): Flow<DataState<T>> {
    return toastError(fragment.activity())
}

fun <T> Flow<DataState<T>>.showToastError(fragment: BaseDialogFragment<*>): Flow<DataState<T>> {
    return toastError(fragment.activity())
}

fun <T> Flow<DataState<T>>.showToastError(fragment: BaseBottomSheetDialogFragment<*>): Flow<DataState<T>> {
    return toastError(fragment.activity())
}

private fun <T> Flow<DataState<T>>.toastError(activity: MainActivity): Flow<DataState<T>> {
    return onError {
        activity.showLongToast(it)
    }
}

fun <R> Flow<DataState<DDPageDataModel<R>>>.showLo2adingDialog(fragment: BasePageFragment<*>): Flow<DataState<DDPageDataModel<R>>> {
    return loadingState {
        fragment.activity().showApiRequestLoadingDialog(it.isLoading)
    }
}



fun Double.toAmount(locale: Locale = Locale("hi", "IN")): String {
    val format = NumberFormat.getCurrencyInstance(locale)
    return format.format(this).replace(".00", "")
}

fun <M, I : ConstraintLayoutItem<M>> ConstraintLayoutList<M, I, Int>.itemOptionSelectedListenerFlow(): Flow<ItemOptionClickFields<M, Any>> {
    val block: suspend ProducerScope<ItemOptionClickFields<M, Any>>.() -> Unit = {

        setItemOptionSelectedListener { selected, item, view, payload ->
            trySend(ItemOptionClickFields(selected, item, view, view.itemIndex, payload))
        }

        awaitClose {
            setItemOptionSelectedListener { _, _, _, _ -> }
        }
    }
    return callbackFlow(block)
}

fun <M, T> Flow<ItemOptionClickFields<M, *>>.payloadAs(): Flow<ItemOptionClickFields<M, T>> {
    return map {
        ItemOptionClickFields(
            it.selected,
            it.item,
            it.itemView,
            it.index,
            it.payLoad as T
        )
    }
}

val Toolbar.menuClickListenerFlow: Flow<MenuItem>
    get() = callbackFlow {
        setOnMenuItemClickListener {
            trySend(it).isSuccess
        }
        awaitClose { setOnClickListener(null) }
    }

val File.size get() = if (!exists()) 0.0 else length().toDouble()
val File.sizeInKb get() = size / 1024
val File.sizeInMb get() = sizeInKb / 1024

fun File.sizeStr(): String = size.toString()
fun File.sizeStrInKb(decimals: Int = 0): String = "%.${decimals}f".format(sizeInKb)
fun File.sizeStrInMb(decimals: Int = 0): String = "%.${decimals}f".format(sizeInMb)

fun File.sizeStrWithBytes(): String = sizeStr() + "b"
fun File.sizeStrWithKb(decimals: Int = 0): String = sizeStrInKb(decimals) + "Kb"
fun File.sizeStrWithMb(decimals: Int = 0): String = sizeStrInMb(decimals) + "Mb"

val Int.ordinal get() = ordinal(this)

fun Resources.getQuantityStringZero(
    quantity: Int,
    @PluralsRes pluralResId: Int,
    @StringRes zeroResId: Int? = null
): String {
    return if (zeroResId != null && quantity == 0) {
        getString(zeroResId)
    } else {
        getQuantityString(pluralResId, quantity, quantity)
    }
}

infix fun String?.emptyOrNull(value: String): String {
    if (isNullOrEmpty())
        return value
    return this
}

fun intervalFlow(interval: Long): Flow<Void?> {
    return endlessFlow.onEach {
        delay(interval)
    }
}

fun intervalxFlow(interval: Long, initialDelay: Boolean = true): Flow<Void?> {
    var isFirst = true
    return endlessFlow.onEach {
        if (initialDelay || !isFirst) {
            delay(interval)
        }
        isFirst = false
    }
}

val endlessFlow = flow<Void?> {
    while (true) {
        emit(null)
    }
}

val MutableStateFlow<Int>.emitRefresh: Unit
    get() {
        value = value.inc()
    }


fun <T> Flow<List<AllDataState<DDPageDataModel<T>>>>.agrimguruEmptyPageState(action: suspend (Boolean) -> Unit): Flow<List<AllDataState<DDPageDataModel<T>>>> {
    return states {
        val isEmpty =
            it.all { it.loading?.isLoading == false && (it.success?.data?.data?.isEmpty() == true || it.success == null) }
        action(isEmpty)
        isEmpty
    }
}

fun FirebaseMessaging.task(): Flow<Task<String>> = callbackFlow {

    token.addOnCompleteListener { task ->
        trySend(task)
    }

    awaitClose { }
}



val Locale.isHindi: Boolean get() = language == "hi" || language == "hi_IN"

fun Locale.setAppLocale(context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        context.getSystemService(
            LocaleManager::class.java
        ).applicationLocales = LocaleList(this)
    } else {
        AppCompatDelegate.setApplicationLocales(LocaleListCompat.create(this))
    }
}
