package com.example.nbateams.presentation.teamslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.nbateams.R
import com.example.nbateams.databinding.TeamsListFragmentBinding
import com.example.nbateams.presentation.teamslist.adapter.TeamsListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class TeamsListFragment : Fragment(R.layout.teams_list_fragment) {

    private val viewModel: TeamsListViewModel by viewModel()
    private lateinit var binding: TeamsListFragmentBinding
    private lateinit var adapter: TeamsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = TeamsListFragmentBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = TeamsListAdapter()
        binding.recyclerViewTeams.adapter = adapter
        setupTeamListObserver()
    }

    private fun setupTeamListObserver(){
        viewModel.teamsListResult.observe(viewLifecycleOwner) {
            adapter.teamsList = it.teams
        }
    }
}