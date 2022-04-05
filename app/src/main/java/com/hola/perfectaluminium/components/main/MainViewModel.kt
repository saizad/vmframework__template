package com.hola.perfectaluminium.components.main

import com.hola.perfectaluminium.Template__CurrentUser
import com.hola.perfectaluminium.api.MainApi
import com.hola.perfectaluminium.components.Template__ViewModel
import com.hola.perfectaluminium.di.main.MainEnvironment

abstract class MainViewModel constructor(
    environment: MainEnvironment,
) : Template__ViewModel(environment){

    val api: MainApi = environment.api
    var currentUserType: Template__CurrentUser = environment.currentUser as Template__CurrentUser

}