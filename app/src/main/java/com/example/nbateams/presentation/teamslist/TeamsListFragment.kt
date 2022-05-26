package com.example.nbateams.presentation.teamslist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
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

private const val LIGHT_THEME = 0
private const val DARK_THEME = 1
private const val FOLLOW_SYSTEM_THEME = 2

class TeamsListFragment : Fragment(R.layout.teams_list_fragment) {

    private val viewModel: TeamsListViewModel by viewModel()
    private lateinit var binding: TeamsListFragmentBinding
    private lateinit var adapter: TeamsListAdapter

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
            when (it.itemId) {
                R.id.themeMenu -> changeAppTheme()
            }
            true
        }
    }

    private fun changeAppTheme() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(getString(R.string.theme_menu))
        val styles = arrayOf(
            getString(R.string.light_theme_description),
            getString(R.string.dark_theme_description),
            getString(
                R.string.system_theme_description
            )
        )
        val sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val checkedItem = sharedPreferences.getInt(getString(R.string.checkbox_selected), 0)

        builder.setSingleChoiceItems(styles, checkedItem) { dialog, selectedTheme ->
            when (selectedTheme) {
                LIGHT_THEME -> saveSelectedTheme(LIGHT_THEME, AppCompatDelegate.MODE_NIGHT_NO)
                DARK_THEME -> saveSelectedTheme(DARK_THEME, AppCompatDelegate.MODE_NIGHT_YES)
                FOLLOW_SYSTEM_THEME -> saveSelectedTheme(
                    FOLLOW_SYSTEM_THEME,
                    AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                )
            }
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }

    private fun saveSelectedTheme(checkBoxItem: Int, selectedTheme: Int) {
        val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putInt(getString(R.string.checkbox_selected), checkBoxItem)
            putInt(getString(R.string.theme_selected), selectedTheme)
            apply()
        }
        AppCompatDelegate.setDefaultNightMode(selectedTheme)
        (activity as AppCompatActivity).delegate.applyDayNight()
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