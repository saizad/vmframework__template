package com.drone.destination.components.otp.login

import android.os.Bundle
import android.view.View
import com.drone.destination.R
import com.drone.destination.components.otp.BaseOTPFragment
import com.drone.destination.databinding.FragmentLoginOtpBinding
import com.drone.destination.models.DroneDestinationAuth
import com.vm.framework.model.DataModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginOTPFragment : BaseOTPFragment<DataModel<DroneDestinationAuth>, LoginOTPViewModel>() {

    private lateinit var binding: FragmentLoginOtpBinding

    override val viewModelClassType: Class<LoginOTPViewModel>
        get() = LoginOTPViewModel::class.java

    override fun layoutRes(): Int {
        return R.layout.fragment_login_otp
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?, recycled: Boolean) {
        super.onViewCreated(view, savedInstanceState, recycled)
        binding = FragmentLoginOtpBinding.bind(view)
    }
}
