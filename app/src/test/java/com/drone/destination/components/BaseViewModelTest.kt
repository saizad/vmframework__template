package com.drone.destination.components

import androidx.lifecycle.SavedStateHandle
import com.drone.destination.BuildConfig
import com.drone.destination.DroneDestinationCurrentUser
import com.drone.destination.api.Api
import com.drone.destination.di.MainEnvironment
import com.drone.destination.utils.TestDispatchers
import com.drone.destination.utils.dataStateApiErrorFlow
import com.drone.destination.utils.mock
import com.vm.framework.ActivityResult
import com.vm.framework.VmFrameworkNetworkRequest
import com.vm.framework.enums.DataState
import com.vm.framework.error.ApiErrorException
import com.vm.framework.utils.dataStateDataModelSuccessFlow
import com.vm.framework.utils.dataStateErrorFlow
import com.vm.framework.utils.dataStateSuccessFlow
import dagger.hilt.android.testing.HiltAndroidRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.anyOrNull
import sa.zad.easypermission.PermissionManager
import java.lang.reflect.Constructor
import java.util.Locale
import javax.inject.Inject
import javax.inject.Named


abstract class BaseViewModelTest<VM : BaseViewModel> {


    @Mock
    lateinit var networkRequest: VmFrameworkNetworkRequest

    @Mock
    lateinit var api: Api

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    lateinit var viewModel: VM

    val stateHandle: SavedStateHandle = SavedStateHandle()
    lateinit var mainEnvironment: MainEnvironment

    @Inject
    lateinit var currentUserType: DroneDestinationCurrentUser


    @Inject
    @Named("nav-result")
    lateinit var activityResultFlow: MutableStateFlow<ActivityResult<*>>

    @Inject
    lateinit var permissionManager: PermissionManager

    @Inject
    lateinit var locale: MutableStateFlow<Locale>

    abstract val viewModelClassType: Class<VM>


    val testDispatchers = TestDispatchers()

    @Before
    open fun init() {
        Dispatchers.setMain(testDispatchers.main)
        MockitoAnnotations.openMocks(this)
        hiltRule.inject()
        mainEnvironment = MainEnvironment::class.java.mock
        Mockito.`when`(mainEnvironment.api).thenReturn(api)
        Mockito.`when`(mainEnvironment.isDebugMode).thenReturn(BuildConfig.DEBUG)
        Mockito.`when`(mainEnvironment.locale).thenReturn(locale)
        Mockito.`when`(mainEnvironment.currentUser).thenReturn(currentUserType)
        Mockito.`when`(mainEnvironment.permissionManager).thenReturn(permissionManager)
        Mockito.`when`(mainEnvironment.activityResultFlow).thenReturn(activityResultFlow)
        Mockito.`when`(mainEnvironment.networkRequest).thenReturn(networkRequest)

        viewModel = buildViewModel()
    }

    fun buildViewModel(): VM {
        val constructor: Constructor<VM> = viewModelClassType.constructors[0] as Constructor<VM>
        return constructor.newInstance(stateHandle, mainEnvironment)
    }

    fun mockNetworkRequest(block: () -> Flow<DataState<Any>>) {
        Mockito.`when`(networkRequest.flowData<Any>(anyOrNull(), Mockito.anyInt()))
            .thenReturn(block.invoke())
    }

    fun <T> mockNetworkRequestDataModelSuccess(requestId: Int, block: suspend () -> T) {
        val flowOf: Flow<DataState<Any>> =
            dataStateDataModelSuccessFlow(requestId) { block.invoke() }
        mockNetworkRequest { flowOf }
    }

    fun <T> mockNetworkRequestSuccess(requestId: Int, block: suspend () -> T) {
        val flowOf: Flow<DataState<Any>> = dataStateSuccessFlow(requestId) { block.invoke() }
        mockNetworkRequest { flowOf }
    }

    fun mockNetworkRequestApiError(requestId: Int, apiErrorException: ApiErrorException) {
        val flowOf: Flow<DataState<Any>> =
            dataStateApiErrorFlow(apiErrorException, requestId)
        mockNetworkRequest { flowOf }
    }

    fun mockNetworkRequestError(requestId: Int, throwable: Throwable) {
        val flowOf: Flow<DataState<Any>> = dataStateErrorFlow(throwable, requestId)
        mockNetworkRequest { flowOf }
    }
}