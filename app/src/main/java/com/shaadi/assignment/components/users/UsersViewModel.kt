package com.shaadi.assignment.components.users

import androidx.lifecycle.viewModelScope
import com.shaadi.assignment.components.AssignmentViewModel
import com.shaadi.assignment.di.MainEnvironment
import com.shaadi.assignment.models.AssignmentUser
import com.shaadi.assignment.repo.UsersRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    usersRepo: UsersRepo,
    environment: MainEnvironment
) : AssignmentViewModel(environment) {

    private val usersRequest = usersRepo.allUsers()

    val usersList = usersRequest
        .stateIn(viewModelScope, SharingStarted.Lazily, null)
        .filterNotNull()

    val selectUser = MutableStateFlow<AssignmentUser?>(null)

    val selectedUser = merge(selectUser, currentUser.currentUserFlow)
        .stateIn(viewModelScope, SharingStarted.Lazily, null)


}
