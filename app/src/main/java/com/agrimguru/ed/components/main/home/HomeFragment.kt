package com.agrimguru.ed.components.main.home

import com.agrimguru.ed.R
import com.agrimguru.ed.components.main.MainFragment
import com.agrimguru.ed.di.main.MainEnvironment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
open class HomeFragment : MainFragment<HomeViewModel>() {

    @Inject
    lateinit var environment: MainEnvironment

    override val viewModelClassType: Class<HomeViewModel>
        get() = HomeViewModel::class.java

    override fun layoutRes(): Int {
        return R.layout.fragment_home
    }
}

