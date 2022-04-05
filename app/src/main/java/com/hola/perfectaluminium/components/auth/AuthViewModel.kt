package com.hola.perfectaluminium.components.auth

import com.hola.perfectaluminium.api.AuthApi
import com.hola.perfectaluminium.components.Template__ViewModel
import com.hola.perfectaluminium.di.auth.AuthEnvironment

abstract class AuthViewModel constructor(
    authEnvironment: AuthEnvironment,
    val api: AuthApi = authEnvironment.api
) : Template__ViewModel(authEnvironment)