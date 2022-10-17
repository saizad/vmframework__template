package com.agrimguru.ed.components

import com.agrimguru.ed.AgrimGuruCurrentUser
import com.vm.framework.Environment
import com.vm.framework.VmFrameworkNetworkRequest
import com.vm.framework.components.VmFrameworkBaseViewModel

abstract class AgrimGuruViewModel(
    environment: Environment
) : VmFrameworkBaseViewModel(environment){

    val agrimGuruRequest: VmFrameworkNetworkRequest = environment.networkRequest

    val currentUser: AgrimGuruCurrentUser get() = environment.currentUser as AgrimGuruCurrentUser
}