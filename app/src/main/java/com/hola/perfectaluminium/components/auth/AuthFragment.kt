package com.hola.perfectaluminium.components.auth

import com.vm.framework.VmFrameworkNetworkRequest
import com.hola.perfectaluminium.components.Template__Fragment
import com.hola.perfectaluminium.di.auth.AuthEnvironment
import javax.inject.Inject

abstract class AuthFragment<V : AuthViewModel> : Template__Fragment<V>() {
    @Inject
    lateinit var authEnvironment: AuthEnvironment

    override val networkRequest: VmFrameworkNetworkRequest by lazy {
        authEnvironment.networkRequest
    }
}

