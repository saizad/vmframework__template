package com.drone.destination.components.phone.login

import android.os.Bundle
import android.view.View
import com.drone.destination.R
import com.drone.destination.components.phone.BasePhoneFragment
import com.drone.destination.databinding.FragmentLoginPhoneBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginPhoneFragment : BasePhoneFragment<LoginPhoneViewModel>() {

    private lateinit var binding: FragmentLoginPhoneBinding

    override val viewModelClassType: Class<LoginPhoneViewModel>
        get() = LoginPhoneViewModel::class.java

    override fun layoutRes(): Int {
        return R.layout.fragment_login_phone
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?, recycled: Boolean) {
        super.onViewCreated(view, savedInstanceState, recycled)
        binding = FragmentLoginPhoneBinding.bind(view)
    }
}
