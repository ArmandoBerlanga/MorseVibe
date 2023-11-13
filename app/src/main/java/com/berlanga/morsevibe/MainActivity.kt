package com.berlanga.morsevibe

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.berlanga.morsevibe.databinding.ActivityMainBinding
import com.berlanga.morsevibe.utils.Communicator

class MainActivity : AppCompatActivity(), Communicator {
    lateinit var sharedBundle: Bundle
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedBundle = Bundle()
        sharedBundle.putString("sharedText", "")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_emisor, R.id.navigation_receptor
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun passData(inputText: String) {
        sharedBundle.putString("sharedText", inputText)
    }
}