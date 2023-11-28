package com.drone.destination.di

import android.app.Application
import android.content.SharedPreferences
import com.drone.destination.BuildConfig
import com.drone.destination.DroneDestinationCurrentUser
import com.drone.destination.api.Api
import com.google.gson.Gson
import com.vm.framework.VmFrameworkEasyRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import sa.zad.easypermission.PermissionManager
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun providesAuthApi(retrofit: VmFrameworkEasyRetrofit): Api {
        return retrofit.provideRetrofit().create(Api::class.java)
    }

    @Singleton
    @Provides
    fun providesRetrofit(
        application: Application, currentUser: DroneDestinationCurrentUser, gson: Gson, isDebug: Boolean
    ): VmFrameworkEasyRetrofit {
        return VmFrameworkEasyRetrofit(
            application,
            currentUser,
            gson,
            BuildConfig.DOMAIN_URL,
            BuildConfig.DEBUG
        )
    }

    @Singleton
    @Provides
    fun providesPermissionManager(sharedPreferences: SharedPreferences): PermissionManager {
        return PermissionManager()
    }

    @Provides
    fun isDebugMode(): Boolean {
        return BuildConfig.DEBUG
    }

}