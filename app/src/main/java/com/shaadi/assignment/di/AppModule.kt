package com.shaadi.assignment.di

import android.app.Application
import android.content.SharedPreferences
import androidx.room.Room
import com.google.gson.Gson
import com.shaadi.assignment.AssignmentCurrentUser
import com.shaadi.assignment.BuildConfig
import com.shaadi.assignment.api.Api
import com.shaadi.assignment.db.ShaadiDatabase
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
        application: Application, currentUser: AssignmentCurrentUser, gson: Gson
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
    fun providesRoomDatabase(application: Application): ShaadiDatabase {
        return Room.databaseBuilder(application, ShaadiDatabase::class.java, "shaadi-db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providesPermissionManager(sharedPreferences: SharedPreferences): PermissionManager {
        return PermissionManager()
    }

}