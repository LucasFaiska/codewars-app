package com.lfaiska.codewars.app.presentation.scenes.base.challengeslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.lfaiska.codewars.app.R
import com.lfaiska.codewars.app.databinding.ViewChallengesListItemBinding
import com.lfaiska.codewars.app.presentation.scenes.base.challengeslist.model.ChallengeListItem

class ChallengesListAdapter :
    RecyclerView.Adapter<ChallengesListAdapter.CompletedChallengesListAdapterViewHolder>() {

    private val completedChallengesList = mutableListOf<ChallengeListItem>()

    fun addCompletedChallenges(challengeList: List<ChallengeListItem>) {
        completedChallengesList.addAll(challengeList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CompletedChallengesListAdapterViewHolder {
        val binding: ViewChallengesListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.view_challenges_list_item,
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
        val binding: ViewChallengesListItemBinding
    ) : RecyclerView.ViewHolder(binding.root)
}