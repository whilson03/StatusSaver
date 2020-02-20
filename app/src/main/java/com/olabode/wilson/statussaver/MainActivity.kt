package com.olabode.wilson.statussaver

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.olabode.wilson.statussaver.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )

        drawerLayout = binding.drawerLayout
        navController = Navigation.findNavController(this, R.id.navHostFragment)

        val topLevelDestinations = setOf(
            R.id.whatsAppFragment, R.id.bizwhatsAppFragment, R.id.gbWhatsAppFragment
            , R.id.settingsFragment
        )

        appBarConfiguration = AppBarConfiguration.Builder(topLevelDestinations)
            .setDrawerLayout(drawerLayout)
            .build()
        // Set up ActionBar
        setSupportActionBar(binding.toolbar)

        setupActionBarWithNavController(navController, appBarConfiguration)

        // Set up navigation menu
        binding.navView.setupWithNavController(navController)


        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.videosViewerFragment ||
                destination.id == R.id.viewFragment
            ) {
                binding.toolbar.setBackgroundColor(resources.getColor(android.R.color.black))
            } else {
                binding.toolbar.setBackgroundColor(resources.getColor(R.color.primaryColor))
            }
            if (topLevelDestinations.contains(destination.id)) {
                drawerLayout.setDrawerLockMode(
                    DrawerLayout.LOCK_MODE_UNLOCKED,
                    GravityCompat.START
                )
            } else {
                drawerLayout.setDrawerLockMode(
                    DrawerLayout.LOCK_MODE_LOCKED_CLOSED,
                    GravityCompat.START
                )
            }


            when (destination.id) {
                R.id.splashFragment -> binding.toolbar.visibility = View.GONE
                else -> {
                    binding.toolbar.visibility = View.VISIBLE
                }
            }

        }

    }



    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }


}
