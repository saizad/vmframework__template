package com.agrimguru.ed.components.main

import com.agrimguru.ed.di.main.MainEnvironment
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    environment: MainEnvironment
) : MainViewModel(environment)