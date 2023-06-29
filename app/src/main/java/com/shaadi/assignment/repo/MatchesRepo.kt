package com.shaadi.assignment.repo

import com.shaadi.assignment.AssignmentCurrentUser
import com.shaadi.assignment.db.ShaadiDatabase
import com.shaadi.assignment.models.Match
import com.shaadi.assignment.models.ShaadiUser
import com.shaadi.assignment.utils.dbRequestSuccessState
import com.vm.framework.enums.DataState
import com.vm.framework.model.DataModel
import com.vm.framework.utils.stateToDataRemoveDataModel
import com.vm.framework.utils.successState
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.take
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MatchesRepo @Inject constructor(
    private val shaadiUsersRepo: ShaadiUsersRepo,
    private val db: ShaadiDatabase,
    private val currentUser: AssignmentCurrentUser
) {

    val fetch = MutableStateFlow<Int?>(null)

    val matchesRequest = fetch.filterNotNull()
        .flatMapLatest { currentUser.loggedInUser() }
        .flatMapLatest {
            db.matchesDao().getAll(it.id).distinctUntilChanged()
                .map { it.map { it.match() } }
                .flatMapLatest {
                    dbRequestSuccessState(-1) { DataModel(it) }
                }
        }
        .stateIn(GlobalScope, SharingStarted.Lazily, DataState.Loading(true, -1))

    private val autoFetchIfEmpty = matchesRequest
        .successState()
        .flatMapLatest {
            if(it.data!!.data.isEmpty()) {
                shaadiUsersRepo.populateData()
            } else {
                emptyFlow()
            }
        }
        .stateIn(GlobalScope, SharingStarted.Lazily, null)
        .filterNotNull()

    private val autoGenerateMatches = autoFetchIfEmpty
        .stateToDataRemoveDataModel()
        .flatMapLatest { shaadiUsers ->
            currentUser.loggedInUser().take(1)
                .map { generateMatches(it.id, shaadiUsers) }
                .flatMapLatest {
                    dbRequestSuccessState(-1) { DataModel(emptyList<Match>()) }
                }
        }
        .stateIn(GlobalScope, SharingStarted.Eagerly, DataState.Loading(true, -1))


    val request = merge(matchesRequest, autoFetchIfEmpty, autoGenerateMatches)

    suspend fun matchAction(match: Match, accept: Boolean) {
        db.matchesDao().insert(match.copy(isAccepted = accept))
    }

     private suspend fun generateMatches(user: Int, shaadiUsers: List<ShaadiUser>): List<Match> {
         val items = shaadiUsers.mapIndexed { index, shaadiUser ->
             Match(0, shaadiUser.id, user).apply {
                 from = shaadiUser
             }
         }
         db.matchesDao().insertAll(items)
         return items
    }

}