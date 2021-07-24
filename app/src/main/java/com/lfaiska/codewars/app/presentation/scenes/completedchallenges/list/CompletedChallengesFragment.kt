package com.lfaiska.codewars.app.presentation.scenes.completedchallenges.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.lfaiska.codewars.app.MainApplication
import com.lfaiska.codewars.app.R
import com.lfaiska.codewars.app.databinding.FragmentCompletedChallengesBinding
import com.lfaiska.codewars.app.presentation.scenes.base.challengeslist.ChallengesListAdapter
import com.lfaiska.codewars.app.presentation.scenes.base.challengeslist.ChallengesListFragment
import com.lfaiska.codewars.app.presentation.scenes.base.challengeslist.ChallengesListViewModel
import javax.inject.Inject

class CompletedChallengesFragment : ChallengesListFragment() {
    @Inject
    lateinit var completedChallengesViewModel: CompletedChallengesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (activity?.applicationContext as MainApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun getViewModel() = completedChallengesViewModel
}