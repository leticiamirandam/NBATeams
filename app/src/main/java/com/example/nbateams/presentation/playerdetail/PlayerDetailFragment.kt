package com.example.nbateams.presentation.playerdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.example.nbateams.R
import com.example.nbateams.databinding.PlayerDetailFragmentBinding
import com.example.nbateams.domain.model.PlayersList
import com.example.nbateams.presentation.common.LoadingScreenContent
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

private const val PLAYER_ID = "playerId"

class PlayerDetailFragment : Fragment(R.layout.player_detail_fragment) {

    private val viewModel: PlayerDetailViewModel by viewModel {
        parametersOf(playerId)
    }
    private lateinit var binding: PlayerDetailFragmentBinding
    private var playerId: Int? = null
    private lateinit var composeView: ComposeView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        composeView = ComposeView(requireContext())
        return composeView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            playerId = it.getInt(PLAYER_ID)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupPlayerDetailObserver()
    }

    private fun setupPlayerDetailObserver() {
        viewModel.playerDetailState.observe(viewLifecycleOwner) {
            composeView.apply {
                setContent {
                    PlayerDetailLayout(
                        player = it.player,
                        isLoading = it.isLoading,
                        backButtonListener = { activity?.onBackPressed() })
                }
            }
        }
    }

    private fun setupErrorObserver() {
        viewModel.isError.observe(viewLifecycleOwner) {
            binding.errorDialog.root.isVisible = it
            binding.detailCard.isVisible = !it
            binding.errorDialog.buttonTryAgain.setOnClickListener {
                viewModel.isError.value = false
                viewModel.getPlayerDetail()
            }
        }
    }

    @Composable
    fun PlayerDetailLayout(
        player: MutableLiveData<PlayersList.Player>,
        isLoading: Boolean,
        backButtonListener: () -> Unit
    ) {
        if (isLoading) {
            LoadingScreenContent()
        } else {
            PlayerDetailContent(
                player = player,
                backButtonClick = backButtonListener
            )
        }
    }
}