package com.shaadi.assignment.repo

import com.shaadi.assignment.api.Api
import com.shaadi.assignment.db.ShaadiDatabase
import com.shaadi.assignment.models.ShaadiUser
import com.vm.framework.VmFrameworkNetworkRequest
import com.vm.framework.enums.DataState
import com.vm.framework.model.DataModel
import com.vm.framework.utils.successMap
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ShaadiUsersRepo @Inject constructor(
    private val db: ShaadiDatabase,
    private val api: Api,
    private val networkRequest: VmFrameworkNetworkRequest
) {

    fun populateData(): Flow<DataState<DataModel<List<ShaadiUser>>>> {
       return networkRequest.flowData(api.randomUsers(), -1)
           .successMap {
                DataModel(db.shaadiUsersDao().insertAndGet(it!!.results))
            }
           .onEach { delay(100) }


    }
}