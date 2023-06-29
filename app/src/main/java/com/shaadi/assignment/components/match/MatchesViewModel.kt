package com.shaadi.assignment.components.match

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.shaadi.assignment.components.AssignmentViewModel
import com.shaadi.assignment.di.MainEnvironment
import com.shaadi.assignment.models.AssignmentUser
import com.shaadi.assignment.models.Match
import com.shaadi.assignment.repo.MatchesRepo
import com.vm.framework.utils.stateToDataRemoveDataModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchesViewModel @Inject constructor(
    private val matchesRepo: MatchesRepo,
    savedStateHandle: SavedStateHandle,
    environment: MainEnvironment
) : AssignmentViewModel(environment) {

    private val autoLogin = savedStateHandle.getLiveData<AssignmentUser>("user").asFlow()
        .onEach {
            currentUser.login(it)
        }
        .stateIn(viewModelScope, SharingStarted.Eagerly, null)
        .filterNotNull()

    val request = matchesRepo.request

    val matches = matchesRepo.matchesRequest.stateToDataRemoveDataModel()
        .stateIn(viewModelScope, SharingStarted.Lazily, null)
        .filterNotNull()

    fun matchAction(accept: Boolean, match: Match) {
        viewModelScope.launch {
            matchesRepo.matchAction(match, accept)
        }
    }

    init {
        matchesRepo.fetch.value = (1..999999999).random()
    }
}
