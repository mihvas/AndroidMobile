package com.example.task3

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task3.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStartBinding
    private var selectedActivity: ActivityType? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val activityTypes = listOf(
            ActivityType(R.drawable.ic_activity_bicycles, "Велосипед"),
            ActivityType(R.drawable.ic_activity_bicycles, "Бег")
        )


        val adapter = StartActivityAdapter(activityTypes) { activity ->
            selectedActivity = activity
        }

        binding.activityRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.activityRecyclerView.adapter = adapter

        binding.btnStart.setOnClickListener {
            binding.initialLayout.visibility = View.GONE
            binding.routeLayout.visibility = View.VISIBLE

            if (selectedActivity != null) {

                binding.activityTitle.text = "Выбрано: ${selectedActivity!!.name}"
            } else {
                binding.activityTitle.text = "Выберите активность!"
            }
        }
    }
}