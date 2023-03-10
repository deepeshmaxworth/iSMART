package com.mespl.ismartkotlin.pta

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.mespl.ismartkotlin.R
import com.mespl.ismartkotlin.databinding.ActivityMainBinding

class DashboardActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var drawerLayout: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        drawerLayout = binding.drawerLayout
        setSupportActionBar(binding.appBarMain.toolbar)
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)

        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main2)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_dashboard,R.id.nav_putaway, R.id.nav_dispatch, R.id.nav_bagsadjustment
            ), drawerLayout
        )
//        val nav_Menu: Menu = navView.menu
//        nav_Menu.findItem(R.id.nav_bagsadjustment).isVisible = false

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main2)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}