package com.hola.perfectaluminium.components.main

import com.hola.perfectaluminium.di.main.MainEnvironment
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    environment: MainEnvironment
) : MainViewModel(environment)