/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.navigation

import android.os.Bundle
import android.text.Layout
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.android.navigation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // TODO (5. ) Add private lateinit var drawerLayout
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @Suppress("UNUSED_VARIABLE")
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        // initialize drawerLayout
        drawerLayout = binding.drawerLayout

        // STEPS FOR ADDING SUPPORT FOR THE UP BUTTON
        // TODO (1.) find the navController from myNavHostFragment
        val navController = this.findNavController(R.id.myNavHostFragment)

        // navigation listener w/in onCreate that gets called whenever the destination changes
        // prevent nav gesture if not on start destination
        navController.addOnDestinationChangedListener { nc: NavController, nd: NavDestination, args: Bundle? ->
            if (nd.id == nc.graph.startDestination) {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            } else {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            }
        }

        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)

        // TODO (2.) link the navController to our ActionBar
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout) // TODO (7. ) Include drawerLayout here
        NavigationUI.setupWithNavController(binding.navView, navController) // TODO (8. ) setup navigation UI, to know about the navigation view

    }

    override fun onSupportNavigateUp(): Boolean { // used Control + O, to override this method
        // return super.onSupportNavigateUp()       ----- before changing its functionality with code below
        val navController = this.findNavController(R.id.myNavHostFragment)
        // return navController.navigateUp()
        return NavigationUI.navigateUp(navController, appBarConfiguration) // so navigationUI can replace the up button with the navigation drawer
        // button when we get to the start destination
    }
}
