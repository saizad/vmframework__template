package com.shaadi.assignment.components.users

import android.content.Context
import android.util.AttributeSet
import com.shaadi.assignment.databinding.ItemAssignmentUserBinding
import com.shaadi.assignment.models.AssignmentUser
import com.vm.framework.FullWidthListItem

class AssignmentUserItem @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FullWidthListItem<AssignmentUser>(context, attrs, defStyleAttr) {

    private lateinit var binding: ItemAssignmentUserBinding

    override fun bind(i: AssignmentUser) {
        binding = ItemAssignmentUserBinding.bind(this)
        super.bind(i)
        binding.userId.text = i.id.toString()
    }

    override fun itemGapSize(): Int {
        return 0
    }

    override fun select(select: Boolean) {
        isSelected = select
    }
}