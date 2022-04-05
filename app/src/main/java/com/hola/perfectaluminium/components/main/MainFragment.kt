package com.hola.perfectaluminium.components.main

import com.vm.framework.VmFrameworkNetworkRequest
import com.hola.perfectaluminium.components.Template__Fragment
import com.hola.perfectaluminium.di.main.MainEnvironment
import javax.inject.Inject

abstract class MainFragment<V : MainViewModel> : Template__Fragment<V>() {
    @Inject
    lateinit var mainEnvironment: MainEnvironment

    override val networkRequest: VmFrameworkNetworkRequest by lazy {
        mainEnvironment.networkRequest
    }
}