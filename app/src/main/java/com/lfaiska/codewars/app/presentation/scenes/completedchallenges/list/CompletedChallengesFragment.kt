package com.lfaiska.codewars.app.presentation.scenes.completedchallenges.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.lfaiska.codewars.app.MainApplication
import com.lfaiska.codewars.app.R
import com.lfaiska.codewars.app.databinding.FragmentCompletedChallengesBinding
import javax.inject.Inject

class CompletedChallengesFragment : Fragment() {
    private lateinit var binding: FragmentCompletedChallengesBinding
    private val completedChallengesAdapter = CompletedChallengesAdapter()

    @Inject
    lateinit var viewModel: CompletedChallengesViewModel

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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_completed_challenges, container, false)
        setupView()
        setupObservers()
        viewModel.setupViewModel("PG1")
        viewModel.loadCompletedChallenges()
        return binding.root
    }

    private fun setupView() {
        val linearLayoutManager = LinearLayoutManager(context)
        binding.completedChallengesList.apply {
            this.adapter = completedChallengesAdapter
            this.layoutManager = linearLayoutManager
        }
    }

    private fun setupObservers() {
        viewModel.completedChallengesList.observe(viewLifecycleOwner, { completedChallengeList ->
            completedChallengesAdapter.addCompletedChallenges(completedChallengeList)
        })
    }
}