package com.hola.perfectaluminium.components.auth.splash

import android.os.Bundle
import android.view.View
import com.hola.perfectaluminium.R
import com.hola.perfectaluminium.components.auth.AuthFragment
import com.hola.perfectaluminium.components.main.MainActivity
import com.vm.framework.utils.lifecycleScopeOnMain
import com.vm.framework.utils.startActivityClear
import com.vm.framework.utils.stateToDataRemoveDataModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.take


@AndroidEntryPoint
open class SplashFragment : AuthFragment<SplashViewModel>() {

    override val viewModelClassType: Class<SplashViewModel>
        get() = SplashViewModel::class.java


    override fun layoutRes(): Int {
        return R.layout.fragment_splash
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScopeOnMain {
            viewModel().result
                .stateToDataRemoveDataModel()
                .take(1)
                .collect {
                    context().startActivityClear<MainActivity>()
                }
        }
    }

}
