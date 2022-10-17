package com.agrimguru.ed.components.main

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.vm.framework.VmFrameworkNetworkRequest
import com.vm.framework.utils.lifecycleScopeOnMain
import com.agrimguru.ed.R
import com.agrimguru.ed.components.TemplateActivity
import com.agrimguru.ed.di.main.MainEnvironment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : TemplateActivity<MainActivityViewModel>() {

    @Inject
    lateinit var mainEnvironment: MainEnvironment

    override val networkRequest: VmFrameworkNetworkRequest by lazy {
         mainEnvironment.networkRequest
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer)
    }

    override val viewModelClassType: Class<MainActivityViewModel>
        get() = MainActivityViewModel::class.java


    override fun navController(): NavController {
        return findNavController(R.id.main_nav)
    }

    override fun onViewReady() {
        super.onViewReady()
        lifecycleScopeOnMain {
            viewModel().currentUserType
                .loggedOutUser()
                .collect {
//                    startActivityClear<AuthActivity>()
                }
        }
    }
}