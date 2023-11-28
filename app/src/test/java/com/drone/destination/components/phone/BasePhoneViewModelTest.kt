package com.drone.destination.components.phone

import app.cash.turbine.test
import com.drone.destination.components.BaseViewModelTest
import com.drone.destination.models.Token
import com.vm.framework.utils.stateToDataRemoveDataModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test
import org.mockito.Mockito


internal abstract class BasePhoneViewModelTest<VM : BasePhoneViewModel> : BaseViewModelTest<VM>() {

    @Test(expected = IllegalStateException::class)
    fun `sendOTP fails with invalid form`() = runTest{
        viewModel.form.phoneField.field = "2939032"
        viewModel.sendOTP()
    }

    @Test
    fun `sendOTP success`() = runTest {

        viewModel.form.phoneField.field = "2939032111"

        val token = Token("fake_token")
        mockNetworkRequestDataModelSuccess(-1) {
            token
        }
        viewModel.sendOTP().stateToDataRemoveDataModel().test {
            assertEquals(token, awaitItem())
        }
    }

    @Test
    fun `dev debug phone body with no phone number`() {
        Mockito.`when`(mainEnvironment.isDebugMode).thenReturn(false)
        assertNull(viewModel.form.phoneField.field)
    }

    @Test
    fun `dev debug phone body with phone number`() {
        Mockito.`when`(mainEnvironment.isDebugMode).thenReturn(true)
        assertNotNull(viewModel.form.phoneField.field)
    }

    @Test
    fun `dev debug phone body with phone number default env`() {
        assertNotNull(viewModel.form.phoneField.field)
    }

}