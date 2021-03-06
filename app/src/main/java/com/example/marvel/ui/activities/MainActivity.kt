package com.example.marvel.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.marvel.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configureNavController()
    }

    private fun configureNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_nav_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    fun replaceFragment(actionId: Int) {
        navController.currentDestination?.id.let { currentDestinationId ->
            if (currentDestinationId != actionId) {
                navController.navigate(actionId, null, null, null)
            }
        }
    }

    fun replaceFragment(direction: NavDirections) {
        navController.navigate(direction)
    }

    fun setupToolbar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }
}
