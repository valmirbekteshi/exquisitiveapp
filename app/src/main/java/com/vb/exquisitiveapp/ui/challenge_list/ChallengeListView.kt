package com.vb.exquisitiveapp.ui.challenge_list

import com.vb.exquisitiveapp.model.Challenge

interface ChallengeListView {

    fun showChallenges(list: List<Challenge>)
}