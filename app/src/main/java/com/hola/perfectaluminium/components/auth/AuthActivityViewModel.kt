package com.hola.perfectaluminium.components.auth

import com.hola.perfectaluminium.di.auth.AuthEnvironment
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthActivityViewModel @Inject constructor(
    authEnvironment: AuthEnvironment
) : AuthViewModel(authEnvironment)