package com.agrimguru.ed.components.auth

import com.agrimguru.ed.api.AuthApi
import com.agrimguru.ed.components.AgrimGuruViewModel
import com.agrimguru.ed.di.auth.AuthEnvironment

abstract class AuthViewModel constructor(
    authEnvironment: AuthEnvironment,
    val api: AuthApi = authEnvironment.api
) : AgrimGuruViewModel(authEnvironment)