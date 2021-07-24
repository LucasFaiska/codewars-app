package com.lfaiska.codewars.app.presentation.scenes.completedchallenges.list

import com.lfaiska.codewars.app.presentation.scenes.base.challengeslist.ChallengesListViewModel
import com.lfaiska.codewars.app.presentation.scenes.base.challengeslist.model.ChallengeListItem
import com.lfaiska.codewars.domain.usecase.GetCompletedChallengesFromUser
import javax.inject.Inject

class CompletedChallengesViewModel @Inject constructor(
    private val getCompletedChallengesFromUser: GetCompletedChallengesFromUser
) : ChallengesListViewModel() {

    override suspend fun performLoadChallenges() {
        val completedChallengesList = getCompletedChallengesFromUser.execute(userName, currentPage)
        if (completedChallengesList.data.isEmpty()) {
            _isEmpty.value = true
        } else {
            _challengesList.postValue(completedChallengesList.data.map {
                ChallengeListItem(
                    it.id,
                    it.name
                )
            })
            totalPages = completedChallengesList.totalPages
            currentPage++
        }
    }
}