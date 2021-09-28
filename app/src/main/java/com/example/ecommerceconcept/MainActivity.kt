package com.example.ecommerceconcept

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.ecommerceconcept.R
import com.example.ecommerceconcept.databinding.ActivityMainBinding


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

        mBinding?.mainNav?.setOnClickListener {
            mNavController.navigate(R.id.navigation_main)
        }
        mBinding?.basketNav?.setOnClickListener {
            mNavController.navigate(R.id.navigation_basket)
        }
        mBinding?.favoritesNav?.setOnClickListener {
            mNavController.navigate(R.id.navigation_favorite)
        }
        mBinding?.profileNav?.setOnClickListener {
            mNavController.navigate(R.id.navigation_profile)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }


}