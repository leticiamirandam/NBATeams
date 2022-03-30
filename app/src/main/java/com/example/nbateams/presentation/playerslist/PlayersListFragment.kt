package com.example.nbateams.presentation.playerslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.nbateams.R
import com.example.nbateams.databinding.PlayersListFragmentBinding
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
        adapter = PlayersListAdapter()
        binding.recyclerViewPlayers.adapter = adapter
        setupPlayersListObserver()
    }

    private fun setupPlayersListObserver(){
        viewModel.playersListResponse.observe(viewLifecycleOwner) {
            adapter.playersList = it.players
        }
    }
}