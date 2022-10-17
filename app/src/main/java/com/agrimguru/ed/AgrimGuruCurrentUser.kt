package com.agrimguru.ed

import com.vm.framework.CurrentUserType
import com.vm.framework.DataStoreWrapper
import com.agrimguru.ed.models.AgrimGuruUser
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class AgrimGuruCurrentUser @Inject constructor(dataStoreWrapper: DataStoreWrapper) :
    CurrentUserType<AgrimGuruUser>(dataStoreWrapper) {

    override val classType: Class<AgrimGuruUser>
        get() = AgrimGuruUser::class.java

    override val token: String?
        get() = null
}