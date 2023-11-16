package com.drone.destination.components

import android.os.Bundle
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.drone.destination.R
import com.drone.destination.di.MainEnvironment
import com.vm.framework.VmFrameworkNetworkRequest
import com.vm.framework.components.VmFrameworkBaseActivity
import com.vm.framework.utils.lifecycleScopeOnMain
import dagger.hilt.android.AndroidEntryPoint
import sa.zad.easypermission.PermissionManager
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : VmFrameworkBaseActivity<MainActivityViewModel>() {

    @Inject
    lateinit var permissionManager: PermissionManager

    override fun permissionManager(): PermissionManager {
        return permissionManager
    }

    @Inject
    lateinit var mainEnvironment: MainEnvironment

    override val networkRequest: VmFrameworkNetworkRequest by lazy {
         mainEnvironment.networkRequest
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.MainTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }

    override fun onSupportNavigateUp() = navController().navigateUp()


    override val viewModelClassType: Class<MainActivityViewModel>
        get() = MainActivityViewModel::class.java


    override fun navController(): NavController {
        return findNavController(R.id.main_nav)
    }

    override fun onViewReady() {
        super.onViewReady()
        lifecycleScopeOnMain {
            viewModel().currentUser
                .loggedOutUser()
                .collect {
//                    startActivityClear<AuthActivity>()
                }
        }
    }
}