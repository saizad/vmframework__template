package com.eduauraa.teacher.components

import com.eduauraa.teacher.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class HomeFragment : EduauraaTeacherFragment<HomeViewModel>() {

    override val viewModelClassType: Class<HomeViewModel>
        get() = HomeViewModel::class.java

    override fun layoutRes(): Int {
        return R.layout.fragment_home
    }


}

