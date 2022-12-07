package com.eduauraa.teacher.components

import com.eduauraa.teacher.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
open class SplashFragment : EduauraaTeacherFragment<SplashViewModel>() {

    override val viewModelClassType: Class<SplashViewModel>
        get() = SplashViewModel::class.java


    override fun layoutRes(): Int {
        return R.layout.fragment_splash
    }

}
