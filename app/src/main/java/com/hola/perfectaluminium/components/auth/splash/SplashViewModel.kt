package com.hola.perfectaluminium.components.auth.splash

import com.vm.framework.enums.DataState
import com.vm.framework.model.DataModel
import com.vm.framework.utils.dataStateSuccessFlow
import com.hola.perfectaluminium.components.auth.AuthViewModel
import com.hola.perfectaluminium.di.auth.AuthEnvironment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
@ExperimentalCoroutinesApi
class SplashViewModel @Inject constructor(
    authEnvironment: AuthEnvironment
) : AuthViewModel(authEnvironment) {

    val result: Flow<DataState<DataModel<Boolean>>> =
        currentUser().isLoggedIn
            .onStart { delay(2000) }
            .flatMapLatest {
                dataStateSuccessFlow(it, 66)
            }

}

