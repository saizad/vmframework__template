package com.hola.perfectaluminium

import com.vm.framework.CurrentUserType
import com.vm.framework.DataStoreWrapper
import com.hola.perfectaluminium.models.Template__User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class Template__CurrentUser @Inject constructor(dataStoreWrapper: DataStoreWrapper) :
    CurrentUserType<Template__User>(dataStoreWrapper) {

    override val classType: Class<Template__User>
        get() = Template__User::class.java

    override val token: String?
        get() = null
}