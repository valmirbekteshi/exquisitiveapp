package com.vb.exquisitiveapp.ui.challenge_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vb.exquisitiveapp.databinding.ChallengeItemBinding
import com.vb.exquisitiveapp.helpers.OnItemClick
import com.vb.exquisitiveapp.model.Challenge


class ChallengeListAdapter(val challenges: List<Challenge>, val itemClick: OnItemClick) :
    RecyclerView.Adapter<ChallengeListAdapter.RecipeViewHolder>() {



  inner class RecipeViewHolder(private val itemBinding: ChallengeItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(challenge: Challenge){

            itemBinding.challengeTitle.text = challenge.title
            itemBinding.challengeDesc.text = challenge.description
            itemBinding.challengeFunFact.text = challenge.fun_fact
            itemBinding.root.setOnClickListener{
                itemClick.OnClick(challenge)
            }

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val itemBinding = ChallengeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe: Challenge = challenges[position]
        holder.bind(recipe)
    }

    override fun getItemCount(): Int {
        return challenges.size
    }



}