package com.example.nbateams.presentation.playerdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.nbateams.R
import com.example.nbateams.databinding.PlayerDetailFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

private const val PLAYER_ID = "playerId"

class PlayerDetailFragment : Fragment(R.layout.player_detail_fragment) {

    private val viewModel: PlayerDetailViewModel by viewModel {
        parametersOf(playerId)
    }
    private lateinit var binding: PlayerDetailFragmentBinding
    private var playerId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            playerId = it.getInt(PLAYER_ID)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
        setupPlayerDetailObserver()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PlayerDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun setupPlayerDetailObserver() {
        viewModel.playerDetail.observe(viewLifecycleOwner) {
            with(binding) {
                playerName.text = it.firstName + " " + it.lastName
                playerTeam.text = it.team.fullName
                playerHeight.text = it.heightFeet + " feet"
                playerWeight.text = it.weightPounds + " pounds"
            }
        }
    }
}