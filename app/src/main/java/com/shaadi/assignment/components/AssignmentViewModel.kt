package com.shaadi.assignment.components

import com.shaadi.assignment.AssignmentCurrentUser
import com.vm.framework.Environment
import com.vm.framework.components.VmFrameworkBaseViewModel

abstract class AssignmentViewModel(
    environment: Environment
) : VmFrameworkBaseViewModel(environment){

    val currentUser: AssignmentCurrentUser get() = environment.currentUser as AssignmentCurrentUser
}