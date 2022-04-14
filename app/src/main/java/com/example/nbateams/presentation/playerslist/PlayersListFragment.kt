package com.example.nbateams.presentation.playerslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.nbateams.R
import com.example.nbateams.databinding.PlayersListFragmentBinding
import com.example.nbateams.domain.model.PlayersList
import com.example.nbateams.presentation.playerslist.adapter.PlayersListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlayersListFragment : Fragment(R.layout.players_list_fragment) {

    private val viewModel: PlayersListViewModel by viewModel()
    private lateinit var binding: PlayersListFragmentBinding
    private lateinit var adapter: PlayersListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PlayersListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = PlayersListAdapter {
            onPlayerItemClick(it)
        }
        binding.recyclerViewPlayers.adapter = adapter
        setupPlayersListObserver()
    }

    private fun onPlayerItemClick(player: PlayersList.Player) {
        val bundle = Bundle().apply {
            putInt("playerId", player.id)
        }
        findNavController().navigate(R.id.playerDetailFragment, bundle)
    }

    private fun setupPlayersListObserver() {
        viewModel.playersList.observe(viewLifecycleOwner){
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }
}