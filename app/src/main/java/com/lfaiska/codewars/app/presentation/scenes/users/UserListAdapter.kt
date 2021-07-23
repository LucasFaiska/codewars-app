package com.lfaiska.codewars.app.presentation.scenes.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.lfaiska.codewars.app.R
import com.lfaiska.codewars.app.databinding.ViewUserListItemBinding
import com.lfaiska.codewars.app.presentation.scenes.users.model.UserListItem

class UserListAdapter(private val listener: UserListAdapterListener) :
    RecyclerView.Adapter<UserListAdapter.UserListViewHolder>() {
    private val userList = ArrayDeque<UserListItem>()

    fun addUser(userListItem: UserListItem) {
        userList.addFirst(userListItem)
        notifyItemInserted(0)

        if (userList.size > MAX_USER_LIST_SIZE) {
            userList.removeLast()
            notifyItemRemoved(MAX_USER_LIST_SIZE)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val binding: ViewUserListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.view_user_list_item,
            parent,
            false
        )

        return UserListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        val userListItem = userList[position]
        holder.binding.user = userListItem

        holder.binding.root.setOnClickListener {
            listener.onUserListItemTouched(userListItem)
        }
    }

    override fun getItemCount() = userList.size

    class UserListViewHolder(
        val binding: ViewUserListItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

    companion object {
        const val MAX_USER_LIST_SIZE = 5
    }
}