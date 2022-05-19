package com.example.nbateams.presentation.teamslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.nbateams.R
import com.example.nbateams.data.cache.room.TeamDao
import com.example.nbateams.databinding.TeamsListFragmentBinding
import com.example.nbateams.domain.model.TeamsList
import com.example.nbateams.presentation.teamslist.adapter.TeamsListAdapter
import kotlinx.android.synthetic.main.error_dialog.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class TeamsListFragment : Fragment(R.layout.teams_list_fragment) {

    private val viewModel: TeamsListViewModel by viewModel()
    private lateinit var binding: TeamsListFragmentBinding
    private lateinit var adapter: TeamsListAdapter
    private lateinit var teamsDao: TeamDao

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = TeamsListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = TeamsListAdapter {
            onTeamItemClick(it)
        }
        binding.recyclerViewTeams.adapter = adapter
        setupToolbar()
        setupTeamListObserver()
        setupLoadingObserver()
        setupErrorObserver()
    }

    private fun setupToolbar() {
        binding.toolbar.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.themeMenu -> changeAppTheme()
            }
            true
        }
    }

    private fun changeAppTheme(){

    }

    private fun onTeamItemClick(team: TeamsList.Team) {
        val bundle = Bundle().apply {
            putInt("teamId", team.id)
            putString("teamPicture", team.picture)
        }
        findNavController().navigate(R.id.teamDetailFragment, bundle)
    }

    private fun setupTeamListObserver() {
        viewModel.teamsListResult.observe(viewLifecycleOwner) {
            adapter.teamsList = it.teams
        }
    }

    private fun setupLoadingObserver() {
        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.loadingProgress.isVisible = it
        }
    }

    private fun setupErrorObserver() {
        viewModel.isError.observe(viewLifecycleOwner) {
            binding.errorDialog.root.isVisible = it
            binding.errorDialog.root.buttonTryAgain.setOnClickListener {
                viewModel.isError.value = false
                viewModel.getTeamsList()
            }
        }
    }
}