package com.example.nbateams.presentation.teamdetail

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
import com.example.nbateams.databinding.TeamDetailFragmentBinding
import com.example.nbateams.domain.model.TeamsList
import com.example.nbateams.presentation.common.LoadingScreenContent
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
            teamId = it.getInt(TEAM_ID)
            teamPicture = it.getString(TEAM_PICTURE)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTeamDetailObserver()
    }

    private fun setupTeamDetailObserver() {
        viewModel.teamDetailState.observe(viewLifecycleOwner) {
            composeView.apply {
                setContent {
                    TeamDetailLayout(
                        teamImage = teamPicture,
                        team = it.team,
                        isLoading = it.isLoading,
                        backButtonListener = { activity?.onBackPressed() }
                    )
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
                viewModel.getTeamDetail()
            }
        }
    }

    @Composable
    fun TeamDetailLayout(
        teamImage: String?,
        team: MutableLiveData<TeamsList.Team>,
        isLoading: Boolean,
        backButtonListener: () -> Unit
    ) {
        if (isLoading) {
            LoadingScreenContent()
        } else {
            TeamDetailContent(
                image = teamImage,
                team = team,
                backButtonClick = backButtonListener
            )
        }
    }
}