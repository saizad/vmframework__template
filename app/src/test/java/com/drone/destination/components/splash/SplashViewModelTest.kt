package com.drone.destination.components.splash

import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import com.drone.destination.ErrorCodes
import com.drone.destination.components.BaseViewModelTest
import com.drone.destination.models.DroneDestinationAuth
import com.drone.destination.models.DroneDestinationUser
import com.drone.destination.utils.alsoPrint
import com.drone.destination.utils.mock
import com.vm.framework.enums.DataState
import com.vm.framework.error.ApiErrorException
import com.vm.framework.model.DataModel
import com.vm.framework.model.ErrorModel
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.annotation.Config
import kotlin.test.assertEquals
import kotlin.test.assertIs


@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
@Config(application = HiltTestApplication::class)
internal class SplashViewModelTest : BaseViewModelTest<SplashViewModel>() {

    override val viewModelClassType: Class<SplashViewModel>
        get() = SplashViewModel::class.java

    @Test
    fun `login expired`() = runTest {

        val error = com.vm.framework.model.Error::class.java.mock
        Mockito.`when`(error.errorCode).thenReturn(ErrorCodes.EXPIRED_TOKEN)

        mockNetworkRequestApiError(-1, ApiErrorException(ErrorModel(error)))

        viewModel.loginExpired.test {
            assertIs<ApiErrorException>(awaitItem())
        }
    }

    @Test
    fun `valid user request all 3 request states`() = runTest {
        viewModel.validUserRequest.test {
            assertEquals(DataState.Loading(true, 1), awaitItem())
            assertIs<DataState.Error>(awaitItem())
            assertEquals(DataState.Loading(false, 1), awaitItem())
        }
    }

    @Test
    fun `user not available`() = runTest {
        viewModel.validUserRequest.test {
            skipItems(1)
            assertIs<DataState.Error>(awaitItem())
            skipItems(1)
        }
    }


    @Test
    fun `user is available with user object`() = runTest {
        val (_, requestId) = logCurrentUserInAndMockNetworkRequest()
        viewModel.validUserRequest.test {
            assertEquals(DataState.Loading(true, requestId), awaitItem())
            val success = awaitItem() as DataState.Success<DataModel<*>>
            assertIs<DroneDestinationAuth>(success.data?.data.alsoPrint())
            assertEquals(DataState.Loading(false, requestId), awaitItem())
        }
    }

    @Test
    fun `valid user last item is user object`() = runTest {
        logCurrentUserInAndMockNetworkRequest()
        viewModel.validUser.test {
            val success = awaitItem() as DataState.Success<*>
            assertIs<DroneDestinationUser>(success.data.alsoPrint())
        }
    }

    private fun logCurrentUserInAndMockNetworkRequest(): Pair<DroneDestinationAuth, Int> {
        val mockUser = DroneDestinationUser::class.java.mock
        viewModel.currentUser.login(mockUser)

        val requestId = -1
        val refreshToken = "${(12345678..99999999).random()}"
        val authToken = "${(12345678..99999999).random()}"
        val droneDestinationAuth = DroneDestinationAuth(refreshToken, authToken, mockUser)

        mockNetworkRequestDataModelSuccess(requestId) {
            droneDestinationAuth
        }

        return droneDestinationAuth to requestId
    }

    @Test
    fun `current user updated`() = runTest {
        val (droneDestinationAuth, _) = logCurrentUserInAndMockNetworkRequest()
        viewModel.validUserRequest.test { skipItems(3) }
        viewModel.currentUser.refreshToken().test {
            assertEquals(droneDestinationAuth.refresh, awaitItem())
            awaitComplete()
        }
    }

}