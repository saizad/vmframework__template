package com.drone.destination.components.splash

import androidx.lifecycle.SavedStateHandle
import com.drone.destination.components.BaseViewModel
import com.drone.destination.di.MainEnvironment
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    environment: MainEnvironment
) : BaseViewModel(environment) {

}
