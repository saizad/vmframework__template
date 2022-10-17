package com.agrimguru.ed.components.auth

import com.vm.framework.VmFrameworkNetworkRequest
import com.agrimguru.ed.components.AgrimGuruFragment
import com.agrimguru.ed.di.auth.AuthEnvironment
import javax.inject.Inject

abstract class AuthFragment<V : AuthViewModel> : AgrimGuruFragment<V>() {
    @Inject
    lateinit var authEnvironment: AuthEnvironment

    override val networkRequest: VmFrameworkNetworkRequest by lazy {
        authEnvironment.networkRequest
    }
}

