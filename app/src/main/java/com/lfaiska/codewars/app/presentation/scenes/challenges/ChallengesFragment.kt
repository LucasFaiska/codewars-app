package com.lfaiska.codewars.app.presentation.scenes.challenges

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.lfaiska.codewars.app.R
import com.lfaiska.codewars.app.databinding.FragmentChallengesBinding

class ChallengesFragment : Fragment() {
    private lateinit var binding: FragmentChallengesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_challenges, container, false)
        return binding.root
    }
}