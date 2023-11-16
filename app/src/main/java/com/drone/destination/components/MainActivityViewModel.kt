package com.drone.destination.components

import com.drone.destination.di.MainEnvironment
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    environment: MainEnvironment
) : BaseViewModel(environment)