package com.yologger.smemo.presentation.screen.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.yologger.smemo.R
import com.yologger.smemo.presentation.screen.main.home.HomeFragment
import com.yologger.smemo.presentation.screen.main.settings.SettingsFragment

class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener, NavigationBarView.OnItemReselectedListener {

    private val frameLayout: FrameLayout by lazy { findViewById(R.id.activity_main_fl) }
    private val bottomNavigationView: BottomNavigationView by lazy { findViewById(R.id.activity_main_bnv) }
    private val homeFragment: HomeFragment by lazy { HomeFragment.newInstance() }
    private val settingsFragment: SettingsFragment by lazy { SettingsFragment.newInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupBottomNavigationView()
    }

    private fun setupBottomNavigationView() {
        supportFragmentManager.beginTransaction().replace(R.id.activity_main_fl, homeFragment).commit()
        bottomNavigationView.setOnItemSelectedListener(this)
        bottomNavigationView.setOnItemReselectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.activity_main_menu_bottom_navigation_view_item_home -> {
                supportFragmentManager.beginTransaction().replace(R.id.activity_main_fl, homeFragment).commit()
                return true
            }
            R.id.activity_main_menu_bottom_navigation_view_item_settings -> {
                supportFragmentManager.beginTransaction().replace(R.id.activity_main_fl, settingsFragment).commit()
                return true
            }
        }
        return false
    }

    override fun onNavigationItemReselected(item: MenuItem) {

    }
}