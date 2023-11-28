package com.drone.destination.components.otp.login

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.drone.destination.components.otp.BaseOTPViewModelTest
import com.drone.destination.models.DroneDestinationAuth
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
internal class LoginOTPViewModelTest : BaseOTPViewModelTest<DataModel<DroneDestinationAuth>, LoginOTPViewModel>() {

    override val viewModelClassType: Class<LoginOTPViewModel>
        get() = LoginOTPViewModel::class.java


    override fun responseValue(): DataModel<DroneDestinationAuth> {
        return DataModel(DroneDestinationAuth::class.java.mock)
    }


}