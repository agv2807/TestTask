package com.example.testtask.content_screen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.testtask.R
import com.example.testtask.content_screen.home_screen.HomeFragment
import com.example.testtask.content_screen.profile_screen.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class ContentActivity : AppCompatActivity() {

    private val profileFragment = ProfileFragment.newInstance()
    private val homeFragment = HomeFragment.newInstance()
    private var currentFragment: Fragment = profileFragment

    private var bottomNavigationView: BottomNavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_content)

        bottomNavigationView = findViewById(R.id.bottom_menu)
        bottomNavigationView?.selectedItemId = R.id.profile

        supportFragmentManager.beginTransaction()
            .add(R.id.container, homeFragment)
            .hide(homeFragment)
            .add(R.id.container, currentFragment)
            .commit()

        bottomNavigationView?.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.profile -> routeToProfile()
                R.id.home -> routeToHome()
            }
            true
        }
    }

    private fun routeToProfile() {
        supportFragmentManager.beginTransaction()
            .show(profileFragment)
            .hide(currentFragment)
            .commit()
        currentFragment = profileFragment
    }

    private fun routeToHome() {
        supportFragmentManager.beginTransaction()
            .show(homeFragment)
            .hide(currentFragment)
            .commit()
        currentFragment = homeFragment
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, ContentActivity::class.java)
        }
    }
}