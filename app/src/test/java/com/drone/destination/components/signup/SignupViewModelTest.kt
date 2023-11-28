package com.drone.destination.components.signup

import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import com.drone.destination.components.BaseViewModelTest
import com.drone.destination.utils.alsoPrint
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.annotation.Config
import kotlin.time.Duration.Companion.milliseconds

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
@Config(application = HiltTestApplication::class)
class SignupViewModelTest : BaseViewModelTest<SignupViewModel>() {

    override val viewModelClassType: Class<SignupViewModel>
        get() = SignupViewModel::class.java


    override fun init() {
        stateHandle["token"] = "fake_token"
        super.init()
    }

    @Test
    fun `dev debug form with no values`() {
        Mockito.`when`(mainEnvironment.isDebugMode).thenReturn(false)
        viewModel = buildViewModel()
        assertNull(viewModel.form.build())
    }

    @Test
    fun `dev debug form with values`() {
        Mockito.`when`(mainEnvironment.isDebugMode).thenReturn(true)
        viewModel = buildViewModel()
        assertNotNull(viewModel.form.build())
    }

    @Test
    fun `dev debug default env`() {
        assertNotNull(viewModel.form.build())
    }

    @Test
    fun `pincode ignore duplicates`() = runTest {
        Mockito.`when`(mainEnvironment.isDebugMode).thenReturn(false)
        viewModel = buildViewModel()
        val pincode = 123456
        viewModel.form.addressForm.pincodeField.field = pincode
        advanceUntilIdle()
        viewModel.pincodeFlow.test(timeout = 10.milliseconds) {
            assertEquals(pincode, awaitItem().alsoPrint())
            viewModel.form.addressForm.pincodeField.field = pincode
            awaitItem().alsoPrint()
        }
    }

    @Test
    fun `pincode valid`() = runTest {
        val pincode = 123456
        viewModel.form.addressForm.pincodeField.field = pincode
        advanceUntilIdle()
        assertEquals(pincode, viewModel.pincodeFlow.value)
    }

    @Test
    fun `pincode invalid`() = runTest {
        val pincode = 12345
        viewModel.form.addressForm.pincodeField.field = pincode
        advanceUntilIdle()
        assertNull(viewModel.pincodeFlow.value)
    }

    @Test
    fun `pincode Address location fetch`() = runTest {
        Mockito.`when`(mainEnvironment.isDebugMode).thenReturn(false)
        viewModel = buildViewModel()
        assertNull(viewModel.form.addressForm.build())
        viewModel.form.addressForm.pincodeField.field = 123456
        advanceUntilIdle()
        assertNotNull(viewModel.form.addressForm.build())
    }

    @Test
    fun `pincode Address location no fetch for invalid pincode`() = runTest {
        Mockito.`when`(mainEnvironment.isDebugMode).thenReturn(false)
        viewModel = buildViewModel()
        viewModel.form.addressForm.pincodeField.field = 12345
        advanceUntilIdle()
        assertNull(viewModel.form.addressForm.build())
    }

}