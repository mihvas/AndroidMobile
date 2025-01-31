package com.example.task3

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.Locale


class Tab1Fragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: RecyclerViewAdapter
    private lateinit var activityViewModel: ActivityViewModel
    private val REQUEST_CODE_DETAIL = 100
    private var items: List<ActivityItem> = emptyList()

    companion object {
        private const val ARG_TYPE = "type"

        const val TYPE_MY_ACTIVITIES = "my_activities"
        const val TYPE_USER_ACTIVITIES = "user_activities"

        fun newInstance(type: String): Tab1Fragment {
            val fragment = Tab1Fragment()
            val args = Bundle()
            args.putString(ARG_TYPE, type)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tab1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recycler_view)
        layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager

        activityViewModel = ViewModelProvider(this).get(ActivityViewModel::class.java)

        val type = arguments?.getString(ARG_TYPE)


        activityViewModel.allActivities.observe(viewLifecycleOwner) { activities ->
            items = when (type) {
                TYPE_MY_ACTIVITIES -> convertToActivityItems(activities)
                TYPE_USER_ACTIVITIES -> listOf(ActivityItem.SectionHeader("Данные пользователей заглушка"))
                else -> emptyList()
            }
            adapter.setItems(items)
        }

        adapter = RecyclerViewAdapter(items) { activity ->
            val position = items.indexOf(activity)
            val intent = Intent(requireContext(), ActivityDetail::class.java)

            intent.putExtra("distance", activity.distance)
            intent.putExtra("time", activity.time)
            intent.putExtra("sport", activity.sport)
            intent.putExtra("date", activity.date)
            intent.putExtra("startHour", activity.startHour)
            intent.putExtra("endHour", activity.endHour)
            intent.putExtra("user_nick", activity.user_nick)
            intent.putExtra("position", position)
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        view.findViewById<ImageView>(R.id.buttonStart).setOnClickListener {

            val intent = Intent(requireContext(), StartActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE_DETAIL && resultCode == Activity.RESULT_OK) {
            val position = data?.getIntExtra("position", -1) ?: -1
            if (position != -1) {
                recyclerView.post {
                    layoutManager.scrollToPositionWithOffset(position, recyclerView.height / 2)
                }
            }
        }
    }


    private fun convertToActivityItems(activities: List<ActivityEntity>): List<ActivityItem> {
        val result = mutableListOf<ActivityItem>()


        if (activities.isNotEmpty()) {
            result.add(ActivityItem.SectionHeader("Сегодня"))
            activities.forEach { activity ->

                val startTime: String =
                    SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(
                        Date(activity.startTime)
                    )

                val startHour: String =
                    SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(
                        Date(activity.startTime)
                    )
                val endHour: String =
                    SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(
                        Date(activity.endTime)
                    )
                val time: String =
                    SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(
                        Date(activity.endTime - activity.startTime)
                    )

                result.add(
                    ActivityItem.Activity(
                        distance = "10.5 км",
                        time = time,
                        sport = activity.type,
                        date = startTime,
                        startHour = startHour,
                        endHour = endHour,
                        user_nick = ""
                    )
                )
            }
        }
        return result
    }
}
