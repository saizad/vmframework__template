package com.drone.destination

import sa.zad.pagedrecyclerlist.ConstraintLayoutItem

data class ItemOptionClickFields<M, T>(
    val selected: Int,
    val item: M,
    val itemView: ConstraintLayoutItem<M>,
    val index: Int,
    val payLoad: T?
)
