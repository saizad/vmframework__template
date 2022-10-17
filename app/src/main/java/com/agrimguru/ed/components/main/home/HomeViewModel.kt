package com.agrimguru.ed.components.main.home

import com.agrimguru.ed.components.main.MainViewModel
import com.agrimguru.ed.di.main.MainEnvironment
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class HomeViewModel @Inject constructor(
    environment: MainEnvironment
) : MainViewModel(environment) {


}