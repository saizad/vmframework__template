package com.eduauraa.teacher.components

import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.eduauraa.teacher.R
import com.eduauraa.teacher.databinding.ActivityMainBinding
import com.eduauraa.teacher.databinding.DrawerLayoutBinding
import com.google.android.material.navigation.NavigationView
import com.vm.framework.utils.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : EduauraaTeacherActivity<MainActivityViewModel>() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    lateinit var drawerLayout: DrawerLayoutBinding
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.MainTheme)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        drawerLayout = DrawerLayoutBinding.bind(binding.navView.findViewById(R.id.drawerMenuContainer))
        binding.drawerLayout.setStatusBarBackgroundColor(context().color(com.vm.framework.R.color.red_300))
        WindowCompat.setDecorFitsSystemWindows(window, false)

        binding.fragmentHost.insetsListener {
            val navigationBarInsets = it.navigationBarInsets
            binding.fragmentHost.setMarginBottom(navigationBarInsets.bottom)
            it
        }

        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentHost) as NavHostFragment? ?: return

        val navController = host.navController

        setupNavigationMenu(navController)

        WindowInsetsControllerCompat(window, binding.drawerLayout).isAppearanceLightStatusBars = true

    }

    override val viewModelClassType: Class<MainActivityViewModel>
        get() = MainActivityViewModel::class.java


    fun openDrawer() {
        binding.drawerLayout.open()
    }

    private fun setupNavigationMenu(navController: NavController) {
        val sideNavView = findViewById<NavigationView>(R.id.nav_view)
        sideNavView?.setupWithNavController(navController)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return (item.onNavDestinationSelected(navController())
                || super.onOptionsItemSelected(item))
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController().navigateUp(appBarConfiguration)
    }

    override fun navController(): NavController {
        return findNavController(R.id.fragmentHost)
    }

    private fun openDrawerFragment(fragment: Int) {
        binding.drawerLayout.close()
        lifecycleScopeOnMainWithDelay(300) {
            openFragment(
                fragment, {}, NavOptions.Builder()
                    .build()
            )
        }
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(Gravity.LEFT)) {
            binding.drawerLayout.close()
        } else {
            super.onBackPressed()
        }
    }
}