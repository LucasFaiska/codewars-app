package com.lfaiska.codewars.app.presentation.scenes.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lfaiska.codewars.app.presentation.component.SingleLiveEvent
import com.lfaiska.codewars.domain.error.UserNotFoundException
import com.lfaiska.codewars.domain.usecase.GetUserByUsername
import kotlinx.coroutines.launch
import javax.inject.Inject

class UsersViewModel @Inject constructor(
    private val getUserByUsername: GetUserByUsername
) : ViewModel() {

    private val _hasUserNotFoundError = SingleLiveEvent<Boolean>()
    val hasUserNotFoundError: LiveData<Boolean> = _hasUserNotFoundError

    fun searchUser(userName: String) {
        viewModelScope.launch {
            try {
                getUserByUsername.execute(userName)
            } catch (exception: Exception) {
                _hasUserNotFoundError.postValue(true)
            }
        }
    }

}
