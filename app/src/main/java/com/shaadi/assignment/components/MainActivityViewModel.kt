package com.shaadi.assignment.components

import com.shaadi.assignment.di.MainEnvironment
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    environment: MainEnvironment
) : AssignmentViewModel(environment)