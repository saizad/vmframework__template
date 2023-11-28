package com.drone.destination.components.otp

import app.cash.turbine.test
import com.drone.destination.components.BaseViewModelTest
import com.drone.destination.models.Token
import com.drone.destination.models.body.VerifyOTP
import com.vm.framework.utils.stateToDataRemoveDataModel
import com.vm.framework.utils.successState
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test
import org.mockito.Mockito
import kotlin.test.assertIs


internal abstract class BaseOTPViewModelTest<R, VM : BaseOTPViewModel<R>> :
    BaseViewModelTest<VM>() {

    abstract fun responseValue(): R

    override fun init() {
        stateHandle["token"] = Token("fake_token")
        stateHandle["phone"] = "9876543212"
        super.init()
    }

    @Test
    fun `form flow non null`() = runTest {
        viewModel.formFlow.test {
            assertNotNull(awaitItem())
        }
    }

    @Test
    fun `validateOTP fails with invalid form`() = runTest {
        Mockito.`when`(mainEnvironment.isDebugMode).thenReturn(false)
        viewModel.validateOTP().test {
            assertIs<IllegalStateException>(awaitError())
        }
    }

    @Test
    fun `validateOTP with not all digits`() = runTest {
        Mockito.`when`(mainEnvironment.isDebugMode).thenReturn(false)

        viewModel.formFlow.test {
            val form = awaitItem()
            form.otp1Field.field = 2
            form.otp2Field.field = 2
            form.otp3Field.field = 2
        }
        viewModel.validateOTP().test {
            assertIs<IllegalStateException>(awaitError())
        }
    }

    @Test
    fun `validateOTP success`() = runTest {
        viewModel.formFlow.test {
            awaitItem().autoFill("1234")
        }

        val responseValue = responseValue()
        mockNetworkRequestSuccess(-1) {
            responseValue
        }

        viewModel.validateOTP().successState().test {
            assertEquals(responseValue, awaitItem().data)
        }
    }

    @Test
    fun `dev debug verify otp null`() = runTest {
        Mockito.`when`(mainEnvironment.isDebugMode).thenReturn(false)
        viewModel.formFlow.test {
            assertNull(awaitItem().build())
        }
    }

    @Test
    fun `dev debug verify otp not null`() = runTest {
        Mockito.`when`(mainEnvironment.isDebugMode).thenReturn(true)
        viewModel.formFlow.test {
            assertNotNull(awaitItem().build())
        }
    }

    @Test
    fun `dev debug verify otp not null default env`() = runTest {
        viewModel.formFlow.test {
            assertNotNull(awaitItem().build())
        }
    }

    @Test
    fun `form flow same object`() = runTest {
        viewModel.formFlow.zip(viewModel.formFlow) { form1, form2 -> form1 == form2 }
            .test {
                assert(awaitItem())
            }
    }

    @Test
    fun `resendOTP response validate`() = runTest {
        val newToken = "new_token"
        mockNetworkRequestDataModelSuccess(-1) {
            Token(newToken)
        }
        viewModel.resendOTP().stateToDataRemoveDataModel().test {
            assert(awaitItem().token == newToken)
        }
    }


    @Test
    fun `resendOTP form rebuild`() = runTest {
        var verifyOTP1: VerifyOTP? = null
        viewModel.formFlow.test {
            verifyOTP1 = awaitItem().requiredBuild()
        }

        mockNetworkRequestDataModelSuccess(-1) {
            Token("new_token")
        }

        viewModel.resendOTP().stateToDataRemoveDataModel().test {
            awaitItem()
        }

        var verifyOTP2: VerifyOTP? = null

        viewModel.formFlow.test {
            verifyOTP2 = awaitItem().requiredBuild()
        }

        assert(verifyOTP1 != verifyOTP2)
    }
}