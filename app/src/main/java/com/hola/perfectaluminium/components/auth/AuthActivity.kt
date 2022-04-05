package com.hola.perfectaluminium.components.auth

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.vm.framework.VmFrameworkNetworkRequest
import com.hola.perfectaluminium.R
import com.hola.perfectaluminium.components.TemplateActivity
import com.hola.perfectaluminium.di.auth.AuthEnvironment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AuthActivity : TemplateActivity<AuthActivityViewModel>() {

    @Inject
    lateinit var authEnvironment: AuthEnvironment

    override val networkRequest: VmFrameworkNetworkRequest by lazy {
        authEnvironment.networkRequest
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AuthTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
    }

    override val viewModelClassType: Class<AuthActivityViewModel>
        get() = AuthActivityViewModel::class.java


    override fun navController(): NavController {
        return findNavController(R.id.auth_nav)
    }
}