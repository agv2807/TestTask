package com.example.testtask.sign_screen

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.testtask.R

class MainActivity : AppCompatActivity(), SignUpFragment.Callbacks {

    private val signInFragment = SignInFragment.newInstance()
    private val signUpFragment = SignUpFragment.newInstance()
    private var currentFragment: Fragment = signUpFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.sign_container, signInFragment)
            .hide(signInFragment)
            .add(R.id.sign_container, currentFragment)
            .commit()
    }

    private fun routeToSignIn() {
        supportFragmentManager.beginTransaction()
            .show(signInFragment)
            .hide(currentFragment)
            .commit()
        currentFragment = signInFragment
    }

    private fun routeToSignUp() {
        supportFragmentManager.beginTransaction()
            .show(signUpFragment)
            .hide(currentFragment)
            .commit()
        currentFragment = signUpFragment
    }

    override fun onLogInPressed() {
        routeToSignIn()
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

}