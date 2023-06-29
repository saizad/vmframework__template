package com.shaadi.assignment.components.match

import android.os.Bundle
import android.view.View
import androidx.core.view.updatePadding
import com.shaadi.assignment.R
import com.shaadi.assignment.components.AssignmentFragment
import com.shaadi.assignment.databinding.FragmentMatchesBinding
import com.vm.framework.utils.apiErrorState
import com.vm.framework.utils.errorState
import com.vm.framework.utils.failOrSuccess
import com.vm.framework.utils.insetsListener
import com.vm.framework.utils.lifecycleScopeOnMain
import com.vm.framework.utils.loadingState
import com.vm.framework.utils.statusBarInsets
import com.vm.framework.utils.toApiErrorData
import com.vm.framework.utils.toErrorData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MatchesFragment : AssignmentFragment<MatchesViewModel>() {

    private lateinit var binding: FragmentMatchesBinding

    override val viewModelClassType: Class<MatchesViewModel>
        get() = MatchesViewModel::class.java

    override fun layoutRes(): Int {
        return R.layout.fragment_matches
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?, recycled: Boolean) {
        super.onViewCreated(view, savedInstanceState, recycled)
        binding = FragmentMatchesBinding.bind(view)

        view.insetsListener {
            binding.appBarLayout.updatePadding(top = it.statusBarInsets.top)
            it
        }

        binding.matchesList.setItemOptionSelectedListener { selected, item, view, payload ->
            viewModel().matchAction(selected == MatchesItem.ACCEPT, item)
        }

        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        lifecycleScopeOnMain {
            viewModel().currentUser.loggedInUser()
                .collectLatest {
                    binding.toolbar.title = "Matches for ${it.id}"
                }
        }

        lifecycleScopeOnMain {
            viewModel().request
                .loadingState { activity().showApiRequestLoadingDialog(it.isLoading) }
                .apiErrorState {
                    showApiErrorDialog(it.toApiErrorData())
                }
                .errorState {
                    showRequestErrorDialog(it.toErrorData())
                }
                .failOrSuccess()
                .collectLatest {
                    activity().showApiRequestLoadingDialog(false)
                }
        }

        lifecycleScopeOnMain {
            viewModel().matches
                .collectLatest {
                    binding.matchesList.listAdapter.items = it
                }
        }
    }
}
