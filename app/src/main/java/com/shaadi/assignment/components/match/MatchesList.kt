package com.shaadi.assignment.components.match

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.shaadi.assignment.R
import com.shaadi.assignment.models.Match
import dagger.hilt.android.AndroidEntryPoint
import sa.zad.pagedrecyclerlist.IntegerPageKeyList


@AndroidEntryPoint
class MatchesList @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0
) : IntegerPageKeyList<Match, MatchesItem>(context, attrs, defStyle) {

    override fun getSelectorItem(context: Context, viewType: Int): MatchesItem {
        return View.inflate(context, R.layout.item_match, null) as MatchesItem
    }

    override fun compare(itemModel1: Match, itemModel2: Match): Boolean {
        return itemModel1 == itemModel2
    }
}