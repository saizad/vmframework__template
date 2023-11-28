package com.drone.destination

import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import app.cash.turbine.turbineScope
import com.drone.destination.models.DroneDestinationAuth
import com.drone.destination.models.DroneDestinationUser
import com.drone.destination.utils.mock
import com.vm.framework.DataStoreWrapper
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.robolectric.annotation.Config
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
@Config(application = HiltTestApplication::class)
class DroneDestinationCurrentUserTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var currentUser: DroneDestinationCurrentUser

    @Inject
    lateinit var dataStoreWrapper: DataStoreWrapper

    @Before
    fun init() {
        MockitoAnnotations.openMocks(this)
        hiltRule.inject()
    }

    @Test
    fun `access token available in datastore`() = runTest {
        val token = "fake_token"
        dataStoreWrapper.putValue(DroneDestinationCurrentUser.ACCESS_TOKEN_PREF_KEY, token)
        val currentUser = DroneDestinationCurrentUser(dataStoreWrapper)
        currentUser.accessToken().test {
            assertSame(token, awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `refresh token available in datastore`() = runTest {
        val token = "fake_token"
        dataStoreWrapper.putValue(DroneDestinationCurrentUser.REFRESH_TOKEN_PREF_KEY, token)
        val currentUser = DroneDestinationCurrentUser(dataStoreWrapper)
        currentUser.refreshToken().test {
            assertSame(token, awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `auth login refresh token available`() = runTest {
        val token = "fake_token"

        currentUser.authLogin(
            DroneDestinationAuth(
                token,
                "asdfasdf",
                DroneDestinationUser::class.java.mock
            )
        )
        currentUser.refreshToken().test {
            assertSame(token, awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `auth login access token available`() = runTest {
        val token = "fake_token"

        currentUser.authLogin(
            DroneDestinationAuth(
                "",
                token,
                DroneDestinationUser::class.java.mock
            )
        )
        currentUser.accessToken().test {
            assertSame(token, awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `auth login token available`() = runTest {
        val token = "fake_token"

        currentUser.authLogin(
            DroneDestinationAuth(
                "",
                token,
                DroneDestinationUser::class.java.mock
            )
        )
        assertSame(currentUser.token, token)
    }

    @Test
    fun `token during start available`() = runTest {
        val token = "fake_token"
        dataStoreWrapper.putValue(DroneDestinationCurrentUser.ACCESS_TOKEN_PREF_KEY, token)
        val currentUser = DroneDestinationCurrentUser(dataStoreWrapper)
        turbineScope {
            assertSame(token, currentUser.token)
        }
    }

    @Test
    fun `token during start not available`() = runTest {
        val currentUser = DroneDestinationCurrentUser(dataStoreWrapper)
        turbineScope {
            assertNull(currentUser.token)
        }
    }
}