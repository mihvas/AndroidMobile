package com.example.task3

import android.graphics.Color
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod

import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(
    private var items: List<ActivityItem>,
    private val onActivityClick: (ActivityItem.Activity) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_SECTION = 0
        private const val TYPE_ACTIVITY = 1
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is ActivityItem.SectionHeader -> TYPE_SECTION
            is ActivityItem.Activity -> TYPE_ACTIVITY
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_SECTION -> SectionViewHolder(
                inflater.inflate(R.layout.date_section_item, parent, false)
            )

            TYPE_ACTIVITY -> ActivityViewHolder(
                inflater.inflate(R.layout.activity_item, parent, false)
            )

            else -> throw IllegalArgumentException("Unknown view type: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = items[position]) {
            is ActivityItem.SectionHeader -> (holder as SectionViewHolder).bind(item)
            is ActivityItem.Activity -> (holder as ActivityViewHolder).bind(item, onActivityClick)
        }
    }

    override fun getItemCount(): Int = items.size


    fun setItems(newItems: List<ActivityItem>) {
        items = newItems
        notifyItemRangeInserted(0, newItems.size)
    }

    class SectionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textView: TextView = view.findViewById(R.id.sectionTextView)
        fun bind(item: ActivityItem.SectionHeader) {
            textView.text = item.date
        }
    }

    class ActivityViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val distanceView: TextView = view.findViewById(R.id.activityDistance)
        private val timeView: TextView = view.findViewById(R.id.activityTime)
        private val sportView: TextView = view.findViewById(R.id.activitySport)
        private val dateView: TextView = view.findViewById(R.id.activityDate)
        private val nickView: TextView = view.findViewById(R.id.activityUser)

        fun bind(item: ActivityItem.Activity, onActivityClick: (ActivityItem.Activity) -> Unit) {
            distanceView.text = item.distance
            timeView.text = item.time
            sportView.text = item.sport
            dateView.text = item.date

            nickView.text = setupClickableText(item.user_nick)
            nickView.movementMethod =LinkMovementMethod.getInstance()

            itemView.setOnClickListener { onActivityClick(item) }
        }

        private fun setupClickableText(text: String): SpannableString {
            val spannableString = SpannableString(text)


            val textStart = 0
            val textEnd = textStart + text.length


            spannableString.setSpan(
                object : ClickableSpan() {
                    override fun onClick(widget: View) {

                    }
                },
                textStart,
                textEnd,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            spannableString.setSpan(
                ForegroundColorSpan(Color.rgb(0,0,255)),
                textStart,
                textEnd,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            return spannableString
        }
    }

}

sealed class ActivityItem {
    data class SectionHeader(val date: String) : ActivityItem()
    data class Activity(
        val distance: String,
        val time: String,
        val sport: String,
        val date: String,
        val startHour: String,
        val endHour: String,
        val user_nick: String
    ) : ActivityItem()
}