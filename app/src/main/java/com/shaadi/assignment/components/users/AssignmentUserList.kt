package com.shaadi.assignment.components.users

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.shaadi.assignment.R
import com.shaadi.assignment.models.AssignmentUser
import dagger.hilt.android.AndroidEntryPoint
import sa.zad.pagedrecyclerlist.IntegerPageKeyList


@AndroidEntryPoint
class AssignmentUserList @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0
) : IntegerPageKeyList<AssignmentUser, AssignmentUserItem>(context, attrs, defStyle) {

    override fun getSelectorItem(context: Context, viewType: Int): AssignmentUserItem {
        return View.inflate(context, R.layout.item_assignment_user, null) as AssignmentUserItem
    }

    override fun compare(itemModel1: AssignmentUser, itemModel2: AssignmentUser): Boolean {
        return itemModel1.id == itemModel2.id
    }
}