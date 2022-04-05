package com.hola.perfectaluminium.di.main

import com.vm.framework.*
import com.hola.perfectaluminium.api.MainApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    fun providesMainApi(retrofit: VmFrameworkEasyRetrofit): MainApi {
        return retrofit.provideRetrofit().create(MainApi::class.java)
    }
}