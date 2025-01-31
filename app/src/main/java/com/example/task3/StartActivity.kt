package com.example.task3

import android.content.Intent
import android.os.Bundle
import android.view.View

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

import androidx.recyclerview.widget.LinearLayoutManager

import com.example.task3.databinding.ActivityStartBinding
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.Locale

class StartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStartBinding
    private val activityViewModel: ActivityViewModel by viewModels()
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

        binding.btnStop.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.btnStart.setOnClickListener {


            if (selectedActivity != null) {
                val newActivity = ActivityEntity(
                    type = selectedActivity?.name.toString(),
                    startTime = System.currentTimeMillis(),
                    endTime = System.currentTimeMillis() + 3600000,
                    coordinates = "lat:123,lon:456"
                )
                activityViewModel.insert(newActivity)

                binding.activityType.text=newActivity.type
                binding.activityDistance.text = "10.5км"

                val time: String =
                    SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(
                        Date(newActivity.endTime - newActivity.startTime)
                    )
                binding.activityTime.text = time

                binding.initialLayout.visibility = View.GONE
                binding.routeLayout.visibility = View.VISIBLE
                binding.activityTitle.text = "Выбрано: $selectedActivity"
            } else {
                binding.activityTitle.text = "Выберите активность!"
            }
        }
    }
}