package com.example.task3

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.task3.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        binding = ActivityMainBinding.inflate(layoutInflater)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, ActivityFragment(), "ACTIVITY_FRAGMENT")
                .commit()
        }


        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_activity -> {
                    switchFragment("ACTIVITY_FRAGMENT", ActivityFragment())
                    true
                }
                R.id.nav_profile -> {
                    switchFragment("PROFILE_FRAGMENT", ProfileFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun switchFragment(tag: String, fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()

        val existingFragment = fragmentManager.findFragmentByTag(tag)

        fragmentManager.fragments.forEach { fragment ->
            transaction.hide(fragment)


        }

        if (existingFragment != null) {

            transaction.show(existingFragment)
        } else {

            transaction.add(R.id.fragment_container, fragment, tag)
        }

        transaction.commitNow()

    }

}