package com.batdemir.stackexchange.ui

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.batdemir.stackexchange.R
import com.batdemir.stackexchange.app.MyApplication
import com.batdemir.stackexchange.databinding.ActivityMainBinding
import com.batdemir.stackexchange.di.component.MainComponent
import com.batdemir.stackexchange.ui.base.BaseActivity
import com.batdemir.stackexchange.utils.setupWithNavController

class MainActivity :
    BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private var currentNavController: LiveData<NavController>? = null
    var mainComponent: MainComponent? = null

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        // Now that BottomNavigationBar has restored its instance state
        // and its selectedItemId, we can proceed with setting up the
        // BottomNavigationBar with Navigation
        setupBottomNavigationBar()
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }

    override fun inject() {
        val applicationComponent = (application as MyApplication).applicationComponent
        applicationComponent.inject(this)
        mainComponent = applicationComponent.mainComponent().create()
    }

    override fun setupDefinition(savedInstanceState: Bundle?) {
        setupBottomNavigationBar()
    }

    override fun setupData() {
        //("Not yet implemented")
    }

    override fun setupListener() {
        //("Not yet implemented")
    }

    /**
     * Called on first creation and when restoring state.
     */
    private fun setupBottomNavigationBar() {
        val navGraphIds = listOf(
            R.navigation.application_navigation,
        )

        // Setup the bottom navigation view with a list of navigation graphs
        val controller = binding!!.bottomNavigationView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = binding!!.navigationHostFragment.id,
            intent = intent
        )

        // Whenever the selected controller changes, setup the action bar.
        controller.observe(
            this,
            { navController -> setupActionBarWithNavController(navController) },
        )
        currentNavController = controller
    }
}