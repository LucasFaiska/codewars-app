package com.lfaiska.codewars.app.presentation.scenes.base.challengeslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.lfaiska.codewars.app.R
import com.lfaiska.codewars.app.databinding.FragmentCompletedChallengesBinding

abstract class ChallengesListFragment : Fragment() {
    private lateinit var binding: FragmentCompletedChallengesBinding
    private val completedChallengesAdapter = ChallengesListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_completed_challenges, container, false)
        setupView()
        setupObservers()
        getViewModel().setupViewModel("PG1")
        getViewModel().loadChallenges()
        return binding.root
    }

    abstract fun getViewModel(): ChallengesListViewModel

    private fun setupView() {
        val linearLayoutManager = LinearLayoutManager(context)
        binding.completedChallengesList.apply {
            this.adapter = completedChallengesAdapter
            this.layoutManager = linearLayoutManager
        }
    }

    private fun setupObservers() {
        getViewModel().challengesList.observe(viewLifecycleOwner, { completedChallengeList ->
            completedChallengesAdapter.addCompletedChallenges(completedChallengeList)
        })

        getViewModel().isLoading.observe(viewLifecycleOwner, { isLoading ->
            binding.isLoading = isLoading
        })
    }
}