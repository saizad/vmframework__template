package com.hola.perfectaluminium.components.main.home

import com.hola.perfectaluminium.R
import com.hola.perfectaluminium.components.main.MainFragment
import com.hola.perfectaluminium.di.main.MainEnvironment
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

