package com.drone.destination.components.splash

import android.os.Bundle
import android.view.View
import com.drone.destination.R
import com.drone.destination.components.BaseFragment
import com.drone.destination.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<SplashViewModel>() {

    private lateinit var binding: FragmentSplashBinding

    override val viewModelClassType: Class<SplashViewModel>
        get() = SplashViewModel::class.java

    override fun layoutRes(): Int {
        return R.layout.fragment_splash
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?, recycled: Boolean) {
        super.onViewCreated(view, savedInstanceState, recycled)
        binding = FragmentSplashBinding.bind(view)
    }
}
