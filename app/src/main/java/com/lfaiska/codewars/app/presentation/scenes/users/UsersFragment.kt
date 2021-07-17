package com.lfaiska.codewars.app.presentation.scenes.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.lfaiska.codewars.app.MainApplication
import com.lfaiska.codewars.app.R
import com.lfaiska.codewars.app.databinding.FragmentUsersBinding
import javax.inject.Inject

class UsersFragment : Fragment() {
    private lateinit var binding: FragmentUsersBinding

    @Inject
    lateinit var usersViewModel: UsersViewModel

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
        return binding.root
    }
}