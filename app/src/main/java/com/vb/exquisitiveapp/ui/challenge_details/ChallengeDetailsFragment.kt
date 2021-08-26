package com.vb.exquisitiveapp.ui.challenge_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.vb.exquisitiveapp.databinding.FragmentChallengeDetailsBinding
import com.vb.exquisitiveapp.model.Challenge

class ChallengeDetailsFragment : Fragment(), ChallengeDetailsView {

    private var challenge_details_binding: FragmentChallengeDetailsBinding? = null
    private val args: ChallengeDetailsFragmentArgs by navArgs()
    private var presenter: ChallengeDetailsPresenter? = null


    private val binding get() = challenge_details_binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        challenge_details_binding =
            FragmentChallengeDetailsBinding.inflate(inflater, container, false)
        presenter = ChallengeDetailsPresenter(this, requireContext())

        val challenge_id = args.challengeId
        presenter?.getChallengeById(challenge_id)



        return binding.root
    }

    override fun showDetails(challange: Challenge) {
        binding.challengeDtTitle.text = challange.title
        binding.challengeDtDesc.text = challange.description
        binding.challengeDtFunFact.text = challange.fun_fact
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.clearDisposable()
    }

}