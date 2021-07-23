package com.lfaiska.codewars.app.presentation.scenes.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lfaiska.codewars.app.MainApplication
import com.lfaiska.codewars.app.R
import com.lfaiska.codewars.app.databinding.FragmentUsersBinding
import com.lfaiska.codewars.app.presentation.scenes.users.model.UserListItem
import javax.inject.Inject

class UsersFragment : Fragment(), UserListAdapterListener {
    private lateinit var binding: FragmentUsersBinding
    private val userListAdapter = UserListAdapter(this)

    @Inject
    lateinit var viewModel: UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (activity?.applicationContext as MainApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_users, container, false)
        setupSearchView()
        setupAdapter()
        setupObservers()
        return binding.root
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.searchUser(query)
                return false
            }
        })
    }

    private fun setupAdapter() {
        binding.userList.apply {
            this.adapter = userListAdapter
            this.layoutManager = LinearLayoutManager(context)
        }
    }

    private fun setupObservers() {
        viewModel.hasUserNameEmptyError.observe(viewLifecycleOwner, { hasFailure ->
            if (hasFailure) {
                AlertDialog.Builder(requireContext())
                    .setMessage(getString(R.string.username_empty_error_message)).show()
            }
        })

        viewModel.hasUserNotFoundError.observe(viewLifecycleOwner, { hasFailure ->
            if (hasFailure) {
                AlertDialog.Builder(requireContext())
                    .setMessage(getString(R.string.user_not_found_error_message)).show()
            }
        })

        viewModel.userListItemFound.observe(viewLifecycleOwner, { userListItem ->
            userListAdapter.addUser(userListItem)
        })
    }

    override fun onUserListItemTouched(userListItem: UserListItem) {
        findNavController().navigate(UsersFragmentDirections.navigateToChallenges())
    }
}