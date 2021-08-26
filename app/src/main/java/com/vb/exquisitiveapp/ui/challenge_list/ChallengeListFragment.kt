package com.vb.exquisitiveapp.ui.challenge_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.vb.exquisitiveapp.R
import com.vb.exquisitiveapp.databinding.FragmentChallengeListBinding
import com.vb.exquisitiveapp.helpers.OnItemClick
import com.vb.exquisitiveapp.model.Challenge

class ChallengeListFragment : Fragment(), ChallengeListView , OnItemClick{

    private val challengeList: List<Challenge> = listOf()
    private var presenter: ChallengeListPresenter? = null
    private var challenge_list_binding: FragmentChallengeListBinding? = null

    private val binding get() = challenge_list_binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        challenge_list_binding = FragmentChallengeListBinding.inflate(inflater, container, false)
        presenter = ChallengeListPresenter(this,requireContext())

        presenter?.getChallenges()

        return binding.root
    }

    override fun showChallenges(list: List<Challenge>) {
        binding.challengeListRv.layoutManager = LinearLayoutManager(context)
        val challengeAdapter = ChallengeListAdapter(list,this)
        binding.challengeListRv.adapter = challengeAdapter
    }


    override fun onDestroy() {
        super.onDestroy()
        presenter?.clearDisposable()
    }

    override fun OnClick(challenge: Challenge) {
        val action = ChallengeListFragmentDirections.actionChallengeListToChallengeDetails().setChallengeId(challenge.id).setChallengeTitle(challenge.title)
        findNavController().navigate(action)
    }
}