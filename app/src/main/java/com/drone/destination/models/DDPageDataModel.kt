package com.drone.destination.models

import com.vm.framework.model.DataModel

open class DDPageDataModel<M>(
    data: List<M>,
    val count: Int,
    val maxPages: Int,
    val nextPage: Int?,
    val previousPage: Int?,
    val page: Int,
    val totalCount: Int,
) : DataModel<List<M>>(data)