package com.agrimguru.ed.components.auth

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.vm.framework.VmFrameworkNetworkRequest
import com.agrimguru.ed.R
import com.agrimguru.ed.components.TemplateActivity
import com.agrimguru.ed.databinding.ActivityAuthBinding
import com.agrimguru.ed.di.auth.AuthEnvironment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AuthActivity : TemplateActivity<AuthActivityViewModel>() {

    @Inject
    lateinit var authEnvironment: AuthEnvironment

    lateinit var binding: ActivityAuthBinding

    override val networkRequest: VmFrameworkNetworkRequest by lazy {
        authEnvironment.networkRequest
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AuthTheme)
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override val viewModelClassType: Class<AuthActivityViewModel>
        get() = AuthActivityViewModel::class.java


    override fun navController(): NavController {
        return findNavController(R.id.auth_nav)
    }
}