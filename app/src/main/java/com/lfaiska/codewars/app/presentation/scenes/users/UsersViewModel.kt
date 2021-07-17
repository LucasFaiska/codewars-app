package com.lfaiska.codewars.app.presentation.scenes.users

import androidx.lifecycle.ViewModel
import com.lfaiska.codewars.domain.usecase.GetUserByUsername
import javax.inject.Inject

class UsersViewModel @Inject constructor(val getUserByUsername: GetUserByUsername) : ViewModel() {

}
