package com.agrimguru.ed.components.auth.splash

import com.agrimguru.ed.R
import com.agrimguru.ed.components.auth.AuthFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
open class SplashFragment : AuthFragment<SplashViewModel>() {

    override val viewModelClassType: Class<SplashViewModel>
        get() = SplashViewModel::class.java


    override fun layoutRes(): Int {
        return R.layout.fragment_splash
    }

}
