package com.drone.destination.components.phone.login

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.drone.destination.components.phone.BasePhoneViewModelTest
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import org.junit.Assert.*
import org.junit.runner.RunWith
import org.robolectric.annotation.Config


@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
@Config(application = HiltTestApplication::class)
internal class LoginPhoneViewModelTest : BasePhoneViewModelTest<LoginPhoneViewModel>() {

    override val viewModelClassType: Class<LoginPhoneViewModel>
        get() = LoginPhoneViewModel::class.java

}