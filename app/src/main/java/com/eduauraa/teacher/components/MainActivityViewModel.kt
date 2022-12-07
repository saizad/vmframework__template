package com.eduauraa.teacher.components

import com.eduauraa.teacher.di.EduauraaTeacherEnvironment
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    authEnvironment: EduauraaTeacherEnvironment
) : EduauraaTeacherViewModel(authEnvironment)

