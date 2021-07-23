package com.lfaiska.codewars.app.presentation.scenes.users

import com.lfaiska.codewars.app.presentation.scenes.users.model.UserListItem

interface UserListAdapterListener {
    fun onUserListItemTouched(userListItem: UserListItem)
}