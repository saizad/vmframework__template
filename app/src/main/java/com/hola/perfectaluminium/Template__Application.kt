package com.hola.perfectaluminium

import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import com.vm.framework.VmFrameworkApplication
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class Template__Application : VmFrameworkApplication(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override fun getWorkManagerConfiguration() =
        Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

}