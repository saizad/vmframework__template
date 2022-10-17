package com.agrimguru.ed.components.main

import com.vm.framework.VmFrameworkNetworkRequest
import com.agrimguru.ed.components.AgrimGuruFragment
import com.agrimguru.ed.di.main.MainEnvironment
import javax.inject.Inject

abstract class MainFragment<V : MainViewModel> : AgrimGuruFragment<V>() {
    @Inject
    lateinit var mainEnvironment: MainEnvironment

    override val networkRequest: VmFrameworkNetworkRequest by lazy {
        mainEnvironment.networkRequest
    }
}