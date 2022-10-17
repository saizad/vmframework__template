package com.agrimguru.ed.di.auth

import com.vm.framework.VmFrameworkEasyRetrofit
import com.agrimguru.ed.api.AuthApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    fun providesAuthApi(retrofit: VmFrameworkEasyRetrofit): AuthApi {
        return retrofit.provideRetrofit().create(AuthApi::class.java)
    }

}