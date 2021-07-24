package com.lfaiska.codewars.app.presentation.scenes.authoredchallenges.list

import com.lfaiska.codewars.app.presentation.scenes.base.challengeslist.ChallengesListViewModel
import com.lfaiska.codewars.app.presentation.scenes.base.challengeslist.model.ChallengeListItem
import com.lfaiska.codewars.domain.usecase.GetAuthoredChallengesFromUser
import com.lfaiska.codewars.domain.usecase.GetCompletedChallengesFromUser
import javax.inject.Inject

class AuthoredChallengesViewModel @Inject constructor(
    private val getAuthoredChallengesFromUser: GetAuthoredChallengesFromUser
) : ChallengesListViewModel() {

    override suspend fun performLoadChallenges() {
        val completedChallengesList = getAuthoredChallengesFromUser.execute(userName, currentPage)
        if (completedChallengesList.data.isEmpty()) {
            _isEmpty.value = true
        } else {
            _challengesList.postValue(completedChallengesList.data.map {
                ChallengeListItem(
                    it.id,
                    it.name
                )
            })
        }
    }
}