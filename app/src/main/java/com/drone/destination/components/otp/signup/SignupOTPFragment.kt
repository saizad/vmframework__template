package com.drone.destination.components.otp.signup

import android.os.Bundle
import android.view.View
import com.drone.destination.R
import com.drone.destination.components.otp.BaseOTPFragment
import com.drone.destination.databinding.FragmentSignupOtpBinding
import com.drone.destination.models.Token
import com.vm.framework.model.DataModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupOTPFragment : BaseOTPFragment<DataModel<Token>, SignupOTPViewModel>() {

    private lateinit var binding: FragmentSignupOtpBinding

    override val viewModelClassType: Class<SignupOTPViewModel>
        get() = SignupOTPViewModel::class.java

    override fun layoutRes(): Int {
        return R.layout.fragment_signup_otp
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?, recycled: Boolean) {
        super.onViewCreated(view, savedInstanceState, recycled)
        binding = FragmentSignupOtpBinding.bind(view)
    }
}
