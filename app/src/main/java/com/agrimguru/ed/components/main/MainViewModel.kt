package com.agrimguru.ed.components.main

import com.agrimguru.ed.AgrimGuruCurrentUser
import com.agrimguru.ed.api.MainApi
import com.agrimguru.ed.components.AgrimGuruViewModel
import com.agrimguru.ed.di.main.MainEnvironment

abstract class MainViewModel constructor(
    environment: MainEnvironment,
) : AgrimGuruViewModel(environment){

    val api: MainApi = environment.api
    var currentUserType: AgrimGuruCurrentUser = environment.currentUser as AgrimGuruCurrentUser

}