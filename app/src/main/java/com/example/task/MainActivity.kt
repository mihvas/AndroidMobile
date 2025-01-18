package com.example.task

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.task.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registerButton.setOnClickListener{
            val intent = Intent(this,SecondActivity::class.java)
            startActivity(intent)

        }

        binding.loginButton.setOnClickListener{
            val intent = Intent(this,ThirdActivity::class.java)
            startActivity(intent)

        }

    }
}

