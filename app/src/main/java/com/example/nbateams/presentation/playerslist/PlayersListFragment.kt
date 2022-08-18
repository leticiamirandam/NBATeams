package com.example.nbateams.presentation.playerslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nbateams.R
import com.example.nbateams.databinding.PlayersListFragmentBinding
import com.example.nbateams.domain.model.PlayersList
import com.example.nbateams.presentation.common.LoadingScreenContent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlayersListFragment : Fragment(R.layout.players_list_fragment) {

    private val viewModel: PlayersListViewModel by viewModel()
    private lateinit var binding: PlayersListFragmentBinding
    private lateinit var composeView: ComposeView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        composeView = ComposeView(requireContext())
        return composeView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupPlayersListObserver()
        //setupErrorObserver()
        //setupFabBackToTop()
    }

    private fun onPlayerItemClick(player: PlayersList.Player) {
        val bundle = Bundle().apply {
            putInt("playerId", player.id)
        }
        findNavController().navigate(R.id.playerDetailFragment, bundle)
    }

    private fun setupPlayersListObserver() {
        viewModel.playersListState.observe(viewLifecycleOwner) {
            composeView.apply {
                setContent {
                    PlayersFragmentLayout(
                        playersList = flowOf(it.players),
                        isLoading = it.isLoading,
                        listener = { onPlayerItemClick(it) }
                    )
                }
            }
        }
    }

    private fun setupFabBackToTop() {
        with(binding) {
            recyclerViewPlayers.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    val layoutManager = recyclerView.layoutManager as? LinearLayoutManager
                    if (layoutManager != null) {
                        fabBackTop.isVisible = layoutManager.findFirstVisibleItemPosition() > 0
                    }
                }
            })
            fabBackTop.setOnClickListener {
                recyclerViewPlayers.smoothScrollToPosition(0)
            }
        }
    }

    private fun setupErrorObserver() {
        viewModel.isError.observe(viewLifecycleOwner) {
            binding.errorDialog.root.isVisible = it
            binding.errorDialog.buttonTryAgain.setOnClickListener {
                viewModel.isError.value = false
                viewModel.getPlayersList()
            }
        }
    }

    @Composable
    fun PlayersFragmentLayout(
        playersList: Flow<PagingData<PlayersList.Player>>,
        isLoading: Boolean,
        listener: (PlayersList.Player) -> Unit,
    ) {
        if (isLoading) {
            LoadingScreenContent()
        } else {
            PlayersListContent(
                players = playersList,
                navigateToDetail = listener
            )
        }
    }
}