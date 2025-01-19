package com.example.task3

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
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
        // Загружаем фрагмент "Активность" при первом запуске
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, ActivityFragment(), "ACTIVITY_FRAGMENT")
                .commit()
            //supportFragmentManager.beginTransaction()
               // .add(R.id.fragment_container, ActivityFragment(), "ACTIVITY_FRAGMENT")
                //.commit()
        }

        // Обрабатываем переключение вкладок
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
        //Log.d("FragmentTransaction", "Before transaction: ${fragmentManager.fragments.map { it.tag }}")

        // Скрываем все фрагменты
        fragmentManager.fragments.forEach { fragment ->
            transaction.hide(fragment)
            //Log.d("FragmentTransaction", "Fragment: ${fragment.tag}, isAdded: ${fragment.isAdded},, isHidden: ${fragment.isHidden}")

        }

        if (existingFragment != null) {
            //Log.d("FragmentTransaction", "Showing existing fragment: $tag")
            transaction.show(existingFragment)
        } else {
            //Log.d("FragmentTransaction", "Adding new fragment: $tag")
            transaction.add(R.id.fragment_container, fragment, tag) // Используем add вместо replace
        }
        //Log.d("FragmentTransaction", "After transaction before commit: ${fragmentManager.fragments.map { it.tag }}")
        transaction.commitNow()
       // Log.d("FragmentTransaction", "After transaction: ${fragmentManager.fragments.map { it.tag }}")
    }

}