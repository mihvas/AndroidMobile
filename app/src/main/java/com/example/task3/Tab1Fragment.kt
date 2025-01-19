package com.example.task3

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Tab1Fragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: RecyclerViewAdapter
    private val REQUEST_CODE_DETAIL = 100

    companion object {
        private const val ARG_TYPE = "type"

        const val TYPE_MY_ACTIVITIES = "my_activities"
        const val TYPE_USER_ACTIVITIES = "user_activities"

        fun newInstance(type: String): Tab1Fragment {
            val fragment =  Tab1Fragment()
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


        val type = arguments?.getString(ARG_TYPE)


        val items = when (type) {
            TYPE_MY_ACTIVITIES -> listOf(
                ActivityItem.SectionHeader("Сегодня"),
                ActivityItem.Activity("14.28км","1 час","Велосипед","29.05.2022",""),

                ActivityItem.SectionHeader("Вчера"),
                ActivityItem.Activity("14.28км","1 час","Велосипед","29.05.2022",""),
            )
            TYPE_USER_ACTIVITIES -> listOf(
                ActivityItem.SectionHeader("Сегодня"),
                ActivityItem.Activity("14.28км","2 час 46 минут","Серфинг \uD83C\uDFC4","Сейчас","@bobr321")

            )
            else -> emptyList()
        }

        adapter = RecyclerViewAdapter(items) { activity ->
            val position = items.indexOf(activity)
            val intent = Intent(requireContext(), ActivityDetail::class.java)
            intent.putExtra("distance", activity.distance)
            intent.putExtra("time", activity.time)
            intent.putExtra("sport", activity.sport)
            intent.putExtra("date", activity.date)
            intent.putExtra("user_nick", activity.user_nick)
            intent.putExtra("position", position)
            startActivity(intent)
        }
        recyclerView.adapter = adapter
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


}

