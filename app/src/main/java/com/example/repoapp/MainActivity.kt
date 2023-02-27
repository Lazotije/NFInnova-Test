package com.example.repoapp

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

class MainActivity : BaseActivity() {

    private lateinit var navController: NavController

    private val graphRootDestinations = setOf(R.id.nav_graph)

    override val layoutId: Int
        get() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupNavigation()
    }

    private fun setupNavigation() {


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        navController.setGraph(R.navigation.nav_graph)
    }

    override fun setShowBack(show: Boolean) {
        super.setShowBack(show)
        lifecycleScope.launchWhenResumed {
            if (!show && (navController.currentDestination?.id in graphRootDestinations)) {
                supportActionBar?.setDisplayShowHomeEnabled(false)
                toolbar.navigationIcon = null
            }
        }
    }
}