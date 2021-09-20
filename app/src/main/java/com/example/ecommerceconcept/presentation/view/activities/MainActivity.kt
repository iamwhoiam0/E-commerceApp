package com.example.ecommerceconcept.presentation.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.ecommerceconcept.R
import com.example.ecommerceconcept.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private var mBinding: ActivityMainBinding? = null
    private lateinit var mNavController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding!!.root)

        setupActionBar()

    }

    private fun setupActionBar() {
        supportActionBar?.hide()

        mNavController = findNavController(R.id.nav_host_fragment)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_main, R.id.navigation_basket, R.id.navigation_favorite, R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(mNavController, appBarConfiguration)
        mBinding?.navView?.setupWithNavController(mNavController)
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }


}