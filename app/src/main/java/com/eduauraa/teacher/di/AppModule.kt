package com.eduauraa.teacher.di

import android.Manifest
import android.app.Application
import android.content.SharedPreferences
import com.eduauraa.teacher.EduauraaTeacherCurrentUser
import com.eduauraa.teacher.BuildConfig
import com.eduauraa.teacher.RequestCodes
import com.eduauraa.teacher.api.Api
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

    @Provides
    fun providesAuthApi(retrofit: VmFrameworkEasyRetrofit): Api {
        return retrofit.provideRetrofit().create(Api::class.java)
    }

    @Singleton
    @Provides
    fun providesRetrofit(
        application: Application, currentUser: EduauraaTeacherCurrentUser, gson: Gson
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