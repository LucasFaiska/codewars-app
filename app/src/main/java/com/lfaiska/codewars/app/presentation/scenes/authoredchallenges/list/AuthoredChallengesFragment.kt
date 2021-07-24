package com.lfaiska.codewars.app.presentation.scenes.authoredchallenges.list

import android.os.Bundle
import com.lfaiska.codewars.app.MainApplication
import com.lfaiska.codewars.app.presentation.scenes.base.challengeslist.ChallengesListFragment
import javax.inject.Inject

class AuthoredChallengesFragment : ChallengesListFragment() {
    @Inject
    lateinit var authoredChallengesViewModel: AuthoredChallengesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (activity?.applicationContext as MainApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun getViewModel() = authoredChallengesViewModel
}