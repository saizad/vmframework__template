package com.drone.destination.components.otp.signup

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.drone.destination.components.otp.BaseOTPViewModelTest
import com.drone.destination.models.Token
import com.drone.destination.utils.mock
import com.vm.framework.model.DataModel
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import org.junit.Assert.*
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
@Config(application = HiltTestApplication::class)
internal class SignupOTPViewModelTest : BaseOTPViewModelTest<DataModel<Token>, SignupOTPViewModel>() {

    override val viewModelClassType: Class<SignupOTPViewModel>
        get() = SignupOTPViewModel::class.java


    override fun responseValue(): DataModel<Token> {
        return DataModel(Token::class.java.mock)
    }

}