package com.example.nbateams.presentation.playerdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
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
        with(binding.toolbar) {
            setNavigationOnClickListener {
                activity?.onBackPressed()
            }
            setNavigationIconTint(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.menu_item_color
                )
            )
        }
        setupPlayerDetailObserver()
        setupLoadingObserver()
        setupErrorObserver()
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
                detailCard.isVisible = true
                playerName.text = "${it.firstName} ${it.lastName}"
                playerTeam.text = it.team.fullName
                playerHeight.text = "${it.heightFeet}${getString(R.string.height_measure_unit)}"
                playerWeight.text = "${it.weightPounds}${getString(R.string.weight_measure_unit)}"
                playerPosition.text = it.position
            }
        }
    }

    private fun setupLoadingObserver(){
        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.loadingProgress.isVisible = it
        }
    }

    private fun setupErrorObserver(){
        viewModel.isError.observe(viewLifecycleOwner) {
            binding.errorDialog.root.isVisible = it
            binding.detailCard.isVisible = !it
            binding.errorDialog.buttonTryAgain.setOnClickListener {
                viewModel.isError.value = false
                viewModel.getPlayerDetail()
            }
        }
    }
}