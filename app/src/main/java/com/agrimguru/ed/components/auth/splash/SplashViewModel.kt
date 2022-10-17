package com.agrimguru.ed.components.auth.splash

import com.agrimguru.ed.components.auth.AuthViewModel
import com.agrimguru.ed.di.auth.AuthEnvironment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    authEnvironment: AuthEnvironment
) : AuthViewModel(authEnvironment)

