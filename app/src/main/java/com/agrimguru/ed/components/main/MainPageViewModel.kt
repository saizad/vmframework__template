package com.agrimguru.ed.components.main

import com.agrimguru.ed.di.main.MainEnvironment
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class MainPageViewModel @Inject constructor(
    mainEnvironment: MainEnvironment
) : MainViewModel(mainEnvironment)