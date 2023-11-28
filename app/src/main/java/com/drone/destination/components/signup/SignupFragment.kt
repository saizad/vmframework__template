package com.drone.destination.components.signup

import android.os.Bundle
import android.view.View
import com.drone.destination.R
import com.drone.destination.components.BaseFragment
import com.drone.destination.databinding.FragmentSignupBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupFragment : BaseFragment<SignupViewModel>() {

    private lateinit var binding: FragmentSignupBinding

    override val viewModelClassType: Class<SignupViewModel>
        get() = SignupViewModel::class.java

    override fun layoutRes(): Int {
        return R.layout.fragment_signup
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?, recycled: Boolean) {
        super.onViewCreated(view, savedInstanceState, recycled)
        binding = FragmentSignupBinding.bind(view)
    }
}
