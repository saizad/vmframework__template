package com.shaadi.assignment.components.match

import android.content.Context
import android.util.AttributeSet
import androidx.core.view.isVisible
import com.shaadi.assignment.databinding.ItemMatchBinding
import com.shaadi.assignment.models.Match
import com.shaadi.assignment.utils.circle
import com.vm.framework.FullWidthListItem
import com.vm.framework.utils.throttleClick

class MatchesItem @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FullWidthListItem<Match>(context, attrs, defStyleAttr) {

    companion object {
        const val ACCEPT = 1
        const val DECLINE = 2
    }

    private lateinit var binding: ItemMatchBinding

    override fun bind(i: Match) {
        binding = ItemMatchBinding.bind(this)
        super.bind(i)
        binding.name.text = i.from.name.fullName
        binding.description.text = i.from.location.location
        binding.avatar.circle(i.from.picture.large)
        binding.actionGroup.isVisible = i.isAccepted == null

        binding.accept.throttleClick {
            callOption(ACCEPT)
        }

        binding.decline.throttleClick {
            callOption(DECLINE)
        }

        binding.matchAction.isVisible = i.isAccepted != null

        binding.matchAction.text = if(i.isAccepted == true) {
            "Accepted"
        } else {
            "Declined"
        }
    }

    override fun itemGapSize(): Int {
        return 0
    }
}