package com.drone.destination.components.phone.signup

import android.os.Bundle
import android.view.View
import com.drone.destination.R
import com.drone.destination.components.phone.BasePhoneFragment
import com.drone.destination.databinding.FragmentSignupPhoneBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupPhoneFragment : BasePhoneFragment<SignupPhoneViewModel>() {

    private lateinit var binding: FragmentSignupPhoneBinding

    override val viewModelClassType: Class<SignupPhoneViewModel>
        get() = SignupPhoneViewModel::class.java

    override fun layoutRes(): Int {
        return R.layout.fragment_signup_phone
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?, recycled: Boolean) {
        super.onViewCreated(view, savedInstanceState, recycled)
        binding = FragmentSignupPhoneBinding.bind(view)
    }
}
