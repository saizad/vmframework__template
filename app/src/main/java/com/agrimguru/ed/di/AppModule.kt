package com.agrimguru.ed.di

import android.Manifest
import android.app.Application
import android.content.SharedPreferences
import com.agrimguru.ed.AgrimGuruCurrentUser
import com.agrimguru.ed.BuildConfig
import com.agrimguru.ed.RequestCodes
import com.google.gson.Gson
import com.vm.framework.VmFrameworkEasyRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import sa.zad.easypermission.AppPermission
import sa.zad.easypermission.AppPermissionImp
import sa.zad.easypermission.PermissionManager
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val MAX_REQUEST = 3

    @Singleton
    @Provides
    fun providesRetrofit(
        application: Application, currentUser: AgrimGuruCurrentUser, gson: Gson
    ): VmFrameworkEasyRetrofit {
        return VmFrameworkEasyRetrofit(application, currentUser, gson, BuildConfig.DOMAIN_URL, BuildConfig.DEBUG)
    }

    @Singleton
    @Provides
    fun providesPermissionManager(sharedPreferences: SharedPreferences): PermissionManager {
        return PermissionManager(
            storagePermission(sharedPreferences),
            locationPermission(sharedPreferences)
        )
    }

    private fun storagePermission(sharedPreferences: SharedPreferences): AppPermission {
        return AppPermissionImp(
            RequestCodes.STORAGE_PERMISSION_REQUEST_CODE, arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ), MAX_REQUEST, sharedPreferences
        )
    }

    private fun locationPermission(sharedPreferences: SharedPreferences): AppPermission {
        return AppPermissionImp(
            RequestCodes.LOCATION_PERMISSION_REQUEST_CODE, arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ), MAX_REQUEST, sharedPreferences
        )
    }

}