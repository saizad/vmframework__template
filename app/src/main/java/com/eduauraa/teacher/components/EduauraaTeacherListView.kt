package com.eduauraa.teacher.components

import android.content.Context
import android.util.AttributeSet
import sa.zad.pagedrecyclerlist.ConstraintLayoutItem
import sa.zad.pagedrecyclerlist.IntegerPageKeyList

abstract class EduauraaTeacherListView<M, I : ConstraintLayoutItem<M>>(
    context: Context,
    attrs: AttributeSet?,
    defStyle: Int
) : IntegerPageKeyList<M, I>(context, attrs, defStyle) {


}