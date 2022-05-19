package com.example.nbateams.presentation.teamdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.nbateams.R
import com.example.nbateams.databinding.TeamDetailFragmentBinding
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.error_dialog.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

private const val TEAM_ID = "teamId"
private const val TEAM_PICTURE = "teamPicture"

class TeamDetailFragment : Fragment(R.layout.team_detail_fragment) {

    private val viewModel: TeamDetailViewModel by viewModel {
        parametersOf(teamId)
    }
    private lateinit var binding: TeamDetailFragmentBinding
    private var teamId: Int? = null
    private var teamPicture: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            teamId = it.getInt(TEAM_ID)
            teamPicture = it.getString(TEAM_PICTURE)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
        Picasso.get()
            .load(teamPicture)
            .placeholder(R.drawable.nba_logo)
            .into(binding.teamPicture)
        setupTeamDetailObserver()
        setupLoadingObserver()
        setupErrorObserver()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = TeamDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun setupTeamDetailObserver() {
        viewModel.teamDetail.observe(viewLifecycleOwner) {
            with(binding) {
                detailCard.isVisible = true
                teamName.text = it.fullName
                teamCity.text = it.city
                teamConference.text = it.conference
                teamDivision.text = it.division
                teamAbbreviation.text = it.abbreviation
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
            binding.errorDialog.root.buttonTryAgain.setOnClickListener {
                viewModel.isError.value = false
                viewModel.getTeamDetail()
            }
        }
    }
}