package com.agrimguru.ed.components.auth

import com.agrimguru.ed.di.auth.AuthEnvironment
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthActivityViewModel @Inject constructor(
    authEnvironment: AuthEnvironment
) : AuthViewModel(authEnvironment)