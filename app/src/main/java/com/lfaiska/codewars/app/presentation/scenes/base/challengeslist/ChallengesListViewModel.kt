package com.lfaiska.codewars.app.presentation.scenes.base.challengeslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lfaiska.codewars.app.presentation.component.SingleLiveEvent
import com.lfaiska.codewars.app.presentation.scenes.base.challengeslist.model.ChallengeListItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class ChallengesListViewModel : ViewModel() {

    protected var currentPage = 0
    protected lateinit var userName: String
    protected var totalPages = 0

    protected val _isLoading = SingleLiveEvent<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    protected val _isEmpty = SingleLiveEvent<Boolean>()
    val isEmpty: LiveData<Boolean> = _isEmpty

    protected val _hasLoadFailure = SingleLiveEvent<Boolean>()
    val hasLoadFailure: LiveData<Boolean> = _hasLoadFailure

    protected val _isUpdating = SingleLiveEvent<Boolean>()
    val isUpdating: LiveData<Boolean> = _isUpdating

    protected val _hasUpdateFailure = SingleLiveEvent<Boolean>()
    val hasUpdatePostsFailure: LiveData<Boolean> = _hasUpdateFailure

    protected val _hasReachedListEnd = SingleLiveEvent<Boolean>()
    val hasReachedListEnd: LiveData<Boolean> = _hasReachedListEnd

    protected val _challengesList = SingleLiveEvent<List<ChallengeListItem>>()
    val challengesList: LiveData<List<ChallengeListItem>> = _challengesList

    fun setupViewModel(userName: String) {
        this.userName = userName
    }

    fun loadChallenges() {
        viewModelScope.launch {
            _isLoading.value = true
            withContext(Dispatchers.IO) {
                try {
                    performLoadChallenges()
                    _isLoading.postValue(false)
                } catch (exception: Exception) {
                    _isLoading.postValue(false)
                    _hasLoadFailure.postValue(true)
                }
            }
        }
    }

    abstract suspend fun performLoadChallenges()
}