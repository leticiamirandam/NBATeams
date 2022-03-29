package com.example.nbateams

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.nbateams.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(R.layout.activity_main) {

   private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setupBottomNavigation()
    }

    private fun setupBottomNavigation(){
        binding.bottomNavigationBar.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.teamsMenuItem -> {
                    navigateToTeamsList()
                }
                R.id.playersMenuItem -> {
                    navigateToPlayersList()
                }
            }
            true
        }
    }

    private fun navigateToTeamsList(){
        Toast.makeText(this, "click", Toast.LENGTH_SHORT).show()
        findNavController(R.id.navHostFragment).navigate(R.id.teamsListFragment)
    }

    private fun navigateToPlayersList(){
        findNavController(R.id.navHostFragment).navigate(R.id.playersListFragment)
    }
}