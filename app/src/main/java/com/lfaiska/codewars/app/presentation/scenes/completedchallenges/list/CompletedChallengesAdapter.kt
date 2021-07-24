package com.lfaiska.codewars.app.presentation.scenes.completedchallenges.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.lfaiska.codewars.app.R
import com.lfaiska.codewars.app.databinding.ViewCompletedChallengesListItemBinding
import com.lfaiska.codewars.app.presentation.scenes.completedchallenges.list.model.CompletedChallengeListItem

class CompletedChallengesAdapter :
    RecyclerView.Adapter<CompletedChallengesAdapter.CompletedChallengesListAdapterViewHolder>() {

    private val completedChallengesList = mutableListOf<CompletedChallengeListItem>()

    fun addCompletedChallenges(completedChallengeList: List<CompletedChallengeListItem>) {
        completedChallengesList.addAll(completedChallengeList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CompletedChallengesListAdapterViewHolder {
        val binding: ViewCompletedChallengesListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.view_completed_challenges_list_item,
            parent,
            false
        )

        return CompletedChallengesListAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CompletedChallengesListAdapterViewHolder, position: Int) {
        holder.binding.challenge = completedChallengesList[position]
    }

    override fun getItemCount() = completedChallengesList.size

    class CompletedChallengesListAdapterViewHolder(
        val binding: ViewCompletedChallengesListItemBinding
    ) : RecyclerView.ViewHolder(binding.root)
}