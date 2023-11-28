package com.drone.destination.components.phone.signup

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.drone.destination.components.phone.BasePhoneViewModelTest
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config


@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
@Config(application = HiltTestApplication::class)
internal class SignupPhoneViewModelTest : BasePhoneViewModelTest<SignupPhoneViewModel>() {

    override val viewModelClassType: Class<SignupPhoneViewModel>
        get() = SignupPhoneViewModel::class.java


    @Test(expected = IllegalStateException::class)
    fun `acceptTermOfUse not checked buildForm failed`() {
        viewModel.form.acceptTermOfUse.field = false
        viewModel.buildForm()
    }

    @Test(expected = IllegalStateException::class)
    fun `acceptPrivacyPolicy checked buildForm failed`() {
        viewModel.form.acceptPrivacyPolicy.field = false
        viewModel.buildForm()
    }
}