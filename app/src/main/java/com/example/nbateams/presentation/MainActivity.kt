package com.example.nbateams.presentation

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.nbateams.R
import com.example.nbateams.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(R.layout.activity_main) {

   private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setupBottomNavigation()
        applySelectedTheme()
    }

    private fun setupBottomNavigation(){
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment?
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationBar)
        bottomNavigationView.setupWithNavController(navHostFragment!!.navController)
    }

    private fun applySelectedTheme(){
        val sharedPreferences = this.getPreferences(Context.MODE_PRIVATE)
        AppCompatDelegate.setDefaultNightMode(sharedPreferences.getInt(getString(R.string.theme_selected), 0))
        (this as AppCompatActivity).delegate.applyDayNight()
    }
}