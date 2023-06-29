package com.shaadi.assignment.components.users

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.core.view.updatePadding
import androidx.navigation.fragment.findNavController
import com.shaadi.assignment.R
import com.shaadi.assignment.components.AssignmentFragment
import com.shaadi.assignment.databinding.FragmentUsersBinding
import com.shaadi.assignment.models.AssignmentUser
import com.vm.framework.utils.insetsListener
import com.vm.framework.utils.lifecycleScopeOnMain
import com.vm.framework.utils.statusBarInsets
import com.vm.framework.utils.throttleClick
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onEach
import sa.zad.pagedrecyclerlist.ListSelection.ListSelectionListener

@AndroidEntryPoint
class UsersFragment : AssignmentFragment<UsersViewModel>() {

    private lateinit var binding: FragmentUsersBinding

    override val viewModelClassType: Class<UsersViewModel>
        get() = UsersViewModel::class.java

    override fun layoutRes(): Int {
        return R.layout.fragment_users
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?, recycled: Boolean) {
        super.onViewCreated(view, savedInstanceState, recycled)
        binding = FragmentUsersBinding.bind(view)

        view.insetsListener {
            binding.appBarLayout.updatePadding(top = it.statusBarInsets.top)
            it
        }

        binding.usersList.setSingleSelection()

        binding.usersList.setSelectionListener(object : ListSelectionListener<AssignmentUser> {
            override fun selected(item: AssignmentUser, selectedList: MutableList<AssignmentUser>) {
                viewModel().selectUser.value = item
            }

            override fun unSelected(
                item: AssignmentUser,
                selectedList: MutableList<AssignmentUser>
            ) {
            }

        })

        binding.enter.throttleClick {
            binding.usersList.selected.firstOrNull()?.let {
                findNavController().navigate(UsersFragmentDirections.actionUsersFragmentToMatchesFragment(it))
            }
        }

        lifecycleScopeOnMain {
            viewModel().usersList
                .onEach {
                    binding.usersList.listAdapter.items = it
                    binding.enter.isVisible = it.isNotEmpty()
                }
                .flatMapLatest { viewModel().selectedUser }
                .collectLatest {
                    binding.enter.isEnabled = it != null
                    if(it != null) {
                        binding.usersList.addSelectedItem(it)
                        binding.enter.text = resources.getString(R.string.login_with_button_text, it.id)
                    } else {
                        binding.usersList.removeAllSelected()
                    }
                }
        }
    }
}
