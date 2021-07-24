package com.lfaiska.codewars.app.presentation.scenes.completedchallenges.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lfaiska.codewars.app.presentation.component.SingleLiveEvent
import com.lfaiska.codewars.app.presentation.scenes.completedchallenges.list.model.CompletedChallengeListItem
import com.lfaiska.codewars.domain.usecase.GetCompletedChallengesFromUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CompletedChallengesViewModel @Inject constructor(
    private val getCompletedChallengesFromUser: GetCompletedChallengesFromUser
) : ViewModel() {

    private var currentPage = 0
    private lateinit var userName: String
    private var totalPages = 0

    private val _isLoading = SingleLiveEvent<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isEmpty = SingleLiveEvent<Boolean>()
    val isEmpty: LiveData<Boolean> = _isEmpty

    private val _hasLoadFailure = SingleLiveEvent<Boolean>()
    val hasLoadFailure: LiveData<Boolean> = _hasLoadFailure

    private val _isUpdating = SingleLiveEvent<Boolean>()
    val isUpdating: LiveData<Boolean> = _isUpdating

    private val _hasUpdateFailure = SingleLiveEvent<Boolean>()
    val hasUpdatePostsFailure: LiveData<Boolean> = _hasUpdateFailure

    private val _hasReachedListEnd = SingleLiveEvent<Boolean>()
    val hasReachedListEnd: LiveData<Boolean> = _hasReachedListEnd

    private val _completedChallengesList = SingleLiveEvent<List<CompletedChallengeListItem>>()
    val completedChallengesList: LiveData<List<CompletedChallengeListItem>> =
        _completedChallengesList

    fun setupViewModel(userName: String) {
        this.userName = userName
    }

    fun loadCompletedChallenges() {
        viewModelScope.launch {
            _isLoading.value = true
            withContext(Dispatchers.IO) {
                try {
                    performLoadCompletedChallenges()
                    _isLoading.postValue(false)
                } catch (exception: Exception) {
                    _isLoading.postValue(false)
                    _hasLoadFailure.postValue(true)
                }
            }
        }
    }

    private suspend fun performLoadCompletedChallenges() {
        val completedChallengesList = getCompletedChallengesFromUser.execute(userName, currentPage)
        if (completedChallengesList.data.isEmpty()) {
            _isEmpty.value = true
        } else {
            _completedChallengesList.postValue(completedChallengesList.data.map {
                CompletedChallengeListItem(
                    it.id,
                    it.name
                )
            })
            totalPages = completedChallengesList.totalPages
            currentPage++
        }
    }

    fun updateCompletedChallenges() {
        viewModelScope.launch {
            _isLoading.value = true
            withContext(Dispatchers.IO) {
                try {
                    performUpdateCompletedChallenges()
                    _isUpdating.postValue(false)
                } catch (exception: Exception) {
                    _isUpdating.postValue(false)
                    _hasUpdateFailure.postValue(true)
                }
            }
        }
    }

    private suspend fun performUpdateCompletedChallenges() {
        if (currentPage == totalPages) {
            _hasReachedListEnd.postValue(true)
        } else {
            val completedChallengesList = getCompletedChallengesFromUser.execute(userName, currentPage)
            _completedChallengesList.postValue(completedChallengesList.data.map {
                CompletedChallengeListItem(
                    it.id,
                    it.name
                )
            })
            currentPage++
        }
    }

}